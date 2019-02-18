package windowManager;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Vector;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used for the page of Updating the artifact
 */
public class Update {
	
	int updateId;
	private Database dbMain;
	private ResultSet currDetails;
	private Statement currStmt;
	private JFrame updateOptions,updateFieldsFrame;
	private JRadioButton artifactRadio,materialRadio,categoryRadio,collectRadio,ownerRadio;
	private ButtonGroup radioGroup;
	private JButton submitButton,updateButton;
	private JPanel radioPanel,fieldsPanel,listPanel,combinedPanel;
	private BoxLayout optionsLayout,radioLayout,combinedLayout,fieldsFrameLayout;
	private GridLayout fieldsPanelLayout,listPanelLayout;
	private JTextField idField,nameField,yearField,locField,remarkField;
	private JTextArea description;
	private JComboBox<String> collectBox,categoryBox,materialBox,ownerBox;
	private JLabel idLabel,nameLabel,yearLabel,locLabel,remarkLabel,descLabel,collectLabel,ownerLabel,materialLabel,categoryLabel,updateLabel;
	
	/**
	 * constructs radio button and panel
	 */
	private void setRadio() {
		radioGroup=new ButtonGroup();
		artifactRadio=new JRadioButton("Artifact",true);
		radioGroup.add(artifactRadio);
		materialRadio=new JRadioButton("Material");
		radioGroup.add(materialRadio);
		categoryRadio=new JRadioButton("Category");
		radioGroup.add(categoryRadio);
		collectRadio=new JRadioButton("Collection");
		radioGroup.add(collectRadio);
		ownerRadio=new JRadioButton("Owner");
		radioGroup.add(ownerRadio);
		radioPanel=new JPanel();
		radioLayout=new BoxLayout(radioPanel,BoxLayout.Y_AXIS);
		radioPanel.setLayout(radioLayout);
		radioPanel.add(artifactRadio);
		radioPanel.add(categoryRadio);
		radioPanel.add(materialRadio);
		radioPanel.add(collectRadio);
		radioPanel.add(ownerRadio);
	}
	
	private void setLabels() {
		nameLabel=new JLabel("Name");
		yearLabel=new JLabel("Year");
		locLabel=new JLabel("Location");
		remarkLabel=new JLabel("Remark");
		descLabel=new JLabel("Description");
		collectLabel=new JLabel("Collection");
		categoryLabel=new JLabel("Category");
		materialLabel=new JLabel("Material");
		ownerLabel=new JLabel("Owner");
		idLabel=new JLabel("ID");
	}
	
	private void setFields() {
		idField=new JTextField(15);
		nameField=new JTextField(15);
		remarkField=new JTextField(15);
		locField=new JTextField(15);
		yearField=new JTextField(15);
		description=new JTextArea(5,15);
	}
	
	private void clearAllInputs() {
		idField.setText("");
		nameField.setText("");
		locField.setText("");
		remarkField.setText("");
		description.setText("");
		yearField.setText("");
	}
	
	private void constructLists() {
		Vector<String> listContents=new Vector<String>();
		listContents=dbMain.getAllEntries("materialName","material");
		materialBox=new JComboBox<String>(listContents);
		listContents=dbMain.getAllEntries("categoryName","category");
		categoryBox=new JComboBox<String>(listContents);
		listContents=dbMain.getAllEntries("ownerName","owner");
		ownerBox=new JComboBox<String>(listContents);
		listContents=dbMain.getAllEntries("givenBy","collection");
		collectBox=new JComboBox<String>(listContents);
		listPanel=new JPanel();
		listPanelLayout=new GridLayout(4,2);
		listPanel.setLayout(listPanelLayout);
		listPanel.add(materialLabel);
		listPanel.add(materialBox);
		listPanel.add(categoryLabel);
		listPanel.add(categoryBox);
		listPanel.add(ownerLabel);
		listPanel.add(ownerBox);
		listPanel.add(collectLabel);
		listPanel.add(collectBox);
	}
	
	private void constructFieldsPanel() {
		fieldsPanelLayout=new GridLayout(5,2);
		fieldsPanel=new JPanel();
		fieldsPanel.setLayout(fieldsPanelLayout);
		fieldsPanel.add(idLabel);
		fieldsPanel.add(idField);
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		fieldsPanel.add(locLabel);
		fieldsPanel.add(locField);
		fieldsPanel.add(remarkLabel);
		fieldsPanel.add(remarkField);
		fieldsPanel.add(yearLabel);
		fieldsPanel.add(yearField);
	}
	
	private void fillLists(String materialName,String categoryName,String ownerName,String collection) {
		try {
			int count=0,i;
			currStmt=dbMain.conn.createStatement();
			currDetails=currStmt.executeQuery("SELECT count(materialid) FROM material");
			if(currDetails.next()) {
				count=currDetails.getInt(1);
			}
			for(i=0;i<count;i++) {
				if(materialBox.getItemAt(i).equals(materialName)) {
					materialBox.setSelectedIndex(i);
				}
			}
			currDetails=currStmt.executeQuery("SELECT count(categoryid) FROM category");
			if(currDetails.next()) {
				count=currDetails.getInt(1);
			}
			for(i=0;i<count;i++) {
				if(categoryBox.getItemAt(i).equals(categoryName)) {
					categoryBox.setSelectedIndex(i);
				}
			}
			currDetails=currStmt.executeQuery("SELECT count(ownerid) FROM owner");
			if(currDetails.next()) {
				count=currDetails.getInt(1);
			}
			for(i=0;i<count;i++) {
				if(ownerBox.getItemAt(i).equals(ownerName)) {
					ownerBox.setSelectedIndex(i);
				}
			}
			currDetails=currStmt.executeQuery("SELECT count(collectid) FROM collection");
			if(currDetails.next()) {
				count=currDetails.getInt(1);
			}
			for(i=0;i<count;i++) {
				if(collectBox.getItemAt(i).equals(collection)) {
					collectBox.setSelectedIndex(i);
				}
			}
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean fillCurrentDetails() {
		try {
			if(artifactRadio.isSelected()) {
				
				currStmt=dbMain.conn.createStatement();
				currDetails=currStmt.executeQuery("SELECT * FROM artifact WHERE artifactid=" + updateId);
				if(currDetails.next()) {
					idField.setText(Integer.toString(currDetails.getInt(1)));
					nameField.setText(currDetails.getString(2));
					locField.setText(currDetails.getString(3));
					remarkField.setText(currDetails.getString(4));
					description.setText(currDetails.getString(5));
					yearField.setText(Integer.toString(currDetails.getInt(6)));
					String materialName=dbMain.getEntryName("materialname","material","materialid",currDetails.getInt(7));
					String categoryName=dbMain.getEntryName("categoryname","category","categoryid",currDetails.getInt(8));
					String ownerName=dbMain.getEntryName("ownername","owner","ownerid",currDetails.getInt(9));
					String collection=dbMain.getEntryName("givenby","collection","collectid",currDetails.getInt(10));
					fillLists(materialName,categoryName,ownerName,collection);
				}
				else {
					JOptionPane.showMessageDialog(null,"There was an error updating the record\nAre you sure you entred the ID right?","Unable to update",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			else if(materialRadio.isSelected() ){
				dbMain.stmt=dbMain.conn.createStatement();
				dbMain.rs=dbMain.stmt.executeQuery("SELECT * FROM material WHERE materialid=" + updateId);
				if(dbMain.rs.next()) {
					idField.setText(Integer.toString(dbMain.rs.getInt(1)));
					nameField.setText(dbMain.rs.getString(2));
				}
				else {
					JOptionPane.showMessageDialog(null,"There was an error updating the record\nAre you sure you entred the ID right?","Unable to update",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			else if(ownerRadio.isSelected()) {
				dbMain.stmt=dbMain.conn.createStatement();
				dbMain.rs=dbMain.stmt.executeQuery("SELECT * FROM owner WHERE ownerid=" + updateId);
				if(dbMain.rs.next()) {
					idField.setText(Integer.toString(dbMain.rs.getInt(1)));
					nameField.setText(dbMain.rs.getString(2));
				}
				else {
					JOptionPane.showMessageDialog(null,"There was an error updating the record\nAre you sure you entred the ID right?","Unable to update",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			else if(categoryRadio.isSelected()) {
				dbMain.stmt=dbMain.conn.createStatement();
				dbMain.rs=dbMain.stmt.executeQuery("SELECT * FROM category WHERE categoryid=" + updateId);
				if(dbMain.rs.next()) {
					idField.setText(Integer.toString(dbMain.rs.getInt(1)));
					nameField.setText(dbMain.rs.getString(2));
				}
				else {
					JOptionPane.showMessageDialog(null,"There was an error updating the record\nAre you sure you entred the ID right?","Unable to update",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			else if(collectRadio.isSelected()) {
				dbMain.stmt=dbMain.conn.createStatement();
				dbMain.rs=dbMain.stmt.executeQuery("SELECT * FROM collection WHERE collectid=" + updateId);
				if(dbMain.rs.next()) {
					idField.setText(Integer.toString(dbMain.rs.getInt(1)));
					nameField.setText(dbMain.rs.getString(2));
					yearField.setText(Integer.toString(dbMain.rs.getInt(3)));
				}
				else {
					JOptionPane.showMessageDialog(null,"There was an error updating the record\nAre you sure you entred the ID right?","Unable to update",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
		return true;
	}
	
	private void artifactFrame() {
		updateFieldsFrame=new JFrame("Update");
		fieldsFrameLayout=new BoxLayout(updateFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		updateFieldsFrame.setLayout(fieldsFrameLayout);
		constructLists();
		constructFieldsPanel();
		combinedPanel=new JPanel();
		combinedLayout=new BoxLayout(combinedPanel,BoxLayout.X_AXIS);
		combinedPanel.setLayout(combinedLayout);
		combinedPanel.add(fieldsPanel);
		combinedPanel.add(listPanel);
		updateFieldsFrame.add(combinedPanel);
		updateFieldsFrame.add(descLabel);
		updateFieldsFrame.add(description);
		updateFieldsFrame.add(updateButton);
		boolean entryFound=fillCurrentDetails();
		if(entryFound==false) {
			updateFieldsFrame.dispose();
			return;
		}
		updateFieldsFrame.pack();
		updateFieldsFrame.setVisible(true);
		updateFieldsFrame.setResizable(false);
		updateFieldsFrame.setLocationRelativeTo(null);
	}
	
	private void genericFrame() {
		updateFieldsFrame=new JFrame();
		if(materialRadio.isSelected()) updateFieldsFrame.setTitle("Material");
		else if(ownerRadio.isSelected()) updateFieldsFrame.setTitle("Owner");
		else if(categoryRadio.isSelected()) updateFieldsFrame.setTitle("Category");
		fieldsFrameLayout=new BoxLayout(updateFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		updateFieldsFrame.setLayout(fieldsFrameLayout);
		fieldsPanel=new JPanel();
		fieldsPanelLayout=new GridLayout(2,2);
		fieldsPanel.setLayout(fieldsPanelLayout);
		idLabel.setText("ID");
		fieldsPanel.add(idLabel);
		fieldsPanel.add(idField);
		nameLabel.setText("Name");
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		updateFieldsFrame.add(fieldsPanel);
		updateFieldsFrame.add(updateButton);
		fillCurrentDetails();
		updateFieldsFrame.pack();
		updateFieldsFrame.setVisible(true);
		updateFieldsFrame.setResizable(false);
		updateFieldsFrame.setLocationRelativeTo(null);
	}
	
	private void collectFrame() {
		updateFieldsFrame=new JFrame("Collection");
		fieldsFrameLayout=new BoxLayout(updateFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		updateFieldsFrame.setLayout(fieldsFrameLayout);
		fieldsPanel=new JPanel();
		fieldsPanelLayout=new GridLayout(3,2);
		fieldsPanel.setLayout(fieldsPanelLayout);
		idLabel.setText("ID");
		fieldsPanel.add(idLabel);
		fieldsPanel.add(idField);
		nameLabel.setText("Given By"); //Here, name refers to the name of the donator
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		yearLabel.setText("Given in Year"); //here, year refers to the year collection was donated
		fieldsPanel.add(yearLabel);
		fieldsPanel.add(yearField);
		updateFieldsFrame.add(fieldsPanel);
		updateFieldsFrame.add(updateButton);
		fillCurrentDetails();
		updateFieldsFrame.pack();
		updateFieldsFrame.setVisible(true);
		updateFieldsFrame.setResizable(false);
		updateFieldsFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * default constructor
	 * constructs frame and adds components
	 */
	public Update(Database dbMain) {
		updateId=0;
		this.dbMain=dbMain;
		setRadio();
		setLabels();
		setFields();
		updateLabel=new JLabel("Update entry in table:");
		submitButton=new JButton("Submit");	
		updateButton=new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String entryName=nameField.getText();
				String entryIdText=idField.getText();
				String yearText=yearField.getText();
				if(entryName.isEmpty() || entryIdText.isEmpty()) {
					JOptionPane.showMessageDialog(null,"No input can be left empty","Empty input",JOptionPane.ERROR_MESSAGE);
					return;
				}
				int entryId=Integer.parseInt(entryIdText);
				try {
					if(artifactRadio.isSelected()) {
						String location=locField.getText();
						String remark=remarkField.getText();
						String descText=description.getText();
						String materialSelected=(String)materialBox.getItemAt(materialBox.getSelectedIndex());
						String categorySelected=(String)categoryBox.getItemAt(categoryBox.getSelectedIndex());
						String ownerSelected=(String)ownerBox.getItemAt(ownerBox.getSelectedIndex());
						String collectSelected=(String)collectBox.getItemAt(collectBox.getSelectedIndex());
						if(yearText.isEmpty() || location.isEmpty() || remark.isEmpty() || descText.isEmpty() || materialSelected.isEmpty() || categorySelected.isEmpty() || ownerSelected.isEmpty() || collectSelected.isEmpty()) {
							JOptionPane.showMessageDialog(null,"No input can be left empty","Empty input",JOptionPane.ERROR_MESSAGE);
							return;
						}
						int materialId=dbMain.getEntryId("materialid","material","materialname",materialSelected);
						int categoryId=dbMain.getEntryId("categoryid","category","categoryname",categorySelected);
						int ownerId=dbMain.getEntryId("ownerid","owner","ownername",ownerSelected);
						int collectId=dbMain.getEntryId("collectid","collection","givenby",collectSelected);
						int year=Integer.parseInt(yearText);
						dbMain.updateArtifact.setInt(1,entryId);
						dbMain.updateArtifact.setString(2,entryName);
						dbMain.updateArtifact.setString(3,location);
						dbMain.updateArtifact.setString(4,remark);
						dbMain.updateArtifact.setString(5,descText);
						dbMain.updateArtifact.setInt(6,year);
						dbMain.updateArtifact.setInt(7,materialId);
						dbMain.updateArtifact.setInt(8,categoryId);
						dbMain.updateArtifact.setInt(9,ownerId);
						dbMain.updateArtifact.setInt(10,collectId);
						dbMain.updateArtifact.setInt(11,updateId);
						dbMain.updateArtifact.execute();
					}
					else if(materialRadio.isSelected()) {
						dbMain.updateMaterial.setInt(1,entryId);
						dbMain.updateMaterial.setString(2,entryName);
						dbMain.updateMaterial.setInt(3,updateId);
						dbMain.updateMaterial.execute();
					}
					else if(ownerRadio.isSelected()) {
						dbMain.updateOwner.setInt(1,entryId);
						dbMain.updateOwner.setString(2,entryName);
						dbMain.updateOwner.setInt(3,updateId);
						dbMain.updateOwner.execute();
					}
					else if(categoryRadio.isSelected()) {
						dbMain.updateCategory.setInt(1,entryId);
						dbMain.updateCategory.setString(2,entryName);
						dbMain.updateCategory.setInt(3,updateId);
						dbMain.updateCategory.execute();
					}
					else if(collectRadio.isSelected()) {
						if(yearText.isEmpty()) {
							JOptionPane.showMessageDialog(null,"No input can be left empty","Empty input",JOptionPane.ERROR_MESSAGE);
							return;
						}
						int year=Integer.parseInt(yearText);
						dbMain.updateCollection.setInt(1,entryId);
						dbMain.updateCollection.setString(2,entryName);
						dbMain.updateCollection.setInt(3,year);
						dbMain.updateCollection.setInt(4,updateId);
						dbMain.updateCollection.execute();
					}
				}
				catch(SQLException sqle) {
					JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
				}
				JOptionPane.showMessageDialog(null,"Your record has been succesfully\nadded to the databse","Success",JOptionPane.INFORMATION_MESSAGE);
				clearAllInputs();
				updateFieldsFrame.dispose();
				updateOptions.dispose();
			}
		});
		updateOptions=new JFrame("Update Options");
		optionsLayout=new BoxLayout(updateOptions.getContentPane(),BoxLayout.Y_AXIS);
		updateOptions.setLayout(optionsLayout);
		updateOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		updateOptions.add(updateLabel);
		updateOptions.add(radioPanel);
		idLabel.setText("Enter ID:");
		updateOptions.add(idLabel);
		updateOptions.add(idField);
		updateOptions.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String queryId=idField.getText();
				if(queryId.isEmpty()) {
					JOptionPane.showMessageDialog(updateOptions,"ID input field is empty","No input",JOptionPane.ERROR_MESSAGE);
					return;
				}
				updateId=Integer.parseInt(queryId);
				if(artifactRadio.isSelected()) artifactFrame();
				else if(materialRadio.isSelected() || ownerRadio.isSelected() || categoryRadio.isSelected()) genericFrame();
				else if(collectRadio.isSelected()) collectFrame();
				updateOptions.dispose();
			}
		});
		updateOptions.setVisible(true);
		updateOptions.pack();
		updateOptions.setResizable(false);
		updateOptions.setLocationRelativeTo(null);
	}
}
