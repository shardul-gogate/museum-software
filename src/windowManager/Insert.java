package windowManager;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.GridLayout;
import java.util.Vector;

/**
 * Still under construction
 * Class for page of Inserting the artifacts
 */
public class Insert {
	
	private Database dbMain;
	private JRadioButton artifactRadio,materialRadio,categoryRadio,collectRadio,ownerRadio;
	private ButtonGroup radioGroup;
	private JButton submitButton,insertButton;
	private JPanel radioPanel,fieldsPanel,listPanel,combinedPanel;
	private JFrame insertOptions,insertFieldsFrame;
	private BoxLayout optionsLayout,fieldsFrameLayout,radioLayout,combinedLayout;
	private GridLayout fieldsPanelLayout,listPanelLayout;
	private JTextField idField,nameField,yearField,locField,remarkField;
	private JTextArea description;
	private JComboBox<String> collectBox,categoryBox,materialBox,ownerBox;
	private JLabel idLabel,nameLabel,yearLabel,locLabel,remarkLabel,descLabel,collectLabel,ownerLabel,materialLabel,categoryLabel,insertLabel;
	
	/**
	 * method that constructs radio buttons and panels
	 */
	private void setRadio() {
		radioPanel=new JPanel();
		radioLayout=new BoxLayout(radioPanel,BoxLayout.Y_AXIS);
		radioPanel.setLayout(radioLayout);
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
	
	/**
	 * this method is invoked if the user wants to insert an artifact
	 */
	private void artifactFrame() {
		insertFieldsFrame=new JFrame("Insert");
		fieldsFrameLayout=new BoxLayout(insertFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		insertFieldsFrame.setLayout(fieldsFrameLayout);
		constructLists();
		constructFieldsPanel();
		combinedPanel=new JPanel();
		combinedLayout=new BoxLayout(combinedPanel,BoxLayout.X_AXIS);
		combinedPanel.setLayout(combinedLayout);
		combinedPanel.add(fieldsPanel);
		combinedPanel.add(listPanel);
		insertFieldsFrame.add(combinedPanel);
		insertFieldsFrame.add(descLabel);
		insertFieldsFrame.add(description);
		insertFieldsFrame.add(insertButton);
		insertFieldsFrame.pack();
		insertFieldsFrame.setVisible(true);
		insertFieldsFrame.setResizable(false);
		insertFieldsFrame.setLocationRelativeTo(null);
	}
	
	private void genericFrame() {
		insertFieldsFrame=new JFrame();
		if(materialRadio.isSelected()) insertFieldsFrame.setTitle("Material");
		else if(ownerRadio.isSelected()) insertFieldsFrame.setTitle("Owner");
		else if(categoryRadio.isSelected()) insertFieldsFrame.setTitle("Category");
		fieldsFrameLayout=new BoxLayout(insertFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		insertFieldsFrame.setLayout(fieldsFrameLayout);
		fieldsPanel=new JPanel();
		fieldsPanelLayout=new GridLayout(2,2);
		fieldsPanel.setLayout(fieldsPanelLayout);
		fieldsPanel.add(idLabel);
		fieldsPanel.add(idField);
		nameLabel.setText("Name");
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		insertFieldsFrame.add(fieldsPanel);
		insertFieldsFrame.add(insertButton);
		insertFieldsFrame.pack();
		insertFieldsFrame.setVisible(true);
		insertFieldsFrame.setResizable(false);
		insertFieldsFrame.setLocationRelativeTo(null);
	}
	
	private void collectFrame() {
		insertFieldsFrame=new JFrame("Collection");
		fieldsFrameLayout=new BoxLayout(insertFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		insertFieldsFrame.setLayout(fieldsFrameLayout);
		fieldsPanel=new JPanel();
		fieldsPanelLayout=new GridLayout(3,2);
		fieldsPanel.setLayout(fieldsPanelLayout);
		fieldsPanel.add(idLabel);
		fieldsPanel.add(idField);
		nameLabel.setText("Given By"); //Here, name refers to the name of the donator
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		yearLabel.setText("Given in Year"); //here, year refers to the year collection was donated
		fieldsPanel.add(yearLabel);
		fieldsPanel.add(yearField);
		insertFieldsFrame.add(fieldsPanel);
		insertFieldsFrame.add(insertButton);
		insertFieldsFrame.pack();
		insertFieldsFrame.setVisible(true);
		insertFieldsFrame.setResizable(false);
		insertFieldsFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * default constructor
	 * constructs frame
	 */
	public Insert(Database dbMain) {
		this.dbMain=dbMain;
		setRadio();
		setLabels();
		setFields();
		insertLabel=new JLabel("Insert");
		insertOptions=new JFrame("Insert Options");
		optionsLayout=new BoxLayout(insertOptions.getContentPane(),BoxLayout.Y_AXIS);
		insertOptions.setLayout(optionsLayout);
		submitButton=new JButton("Submit");
		insertButton=new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
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
						String materialSelected=materialBox.getItemAt(materialBox.getSelectedIndex());
						String categorySelected=categoryBox.getItemAt(categoryBox.getSelectedIndex());
						String ownerSelected=ownerBox.getItemAt(ownerBox.getSelectedIndex());
						String collectSelected=collectBox.getItemAt(collectBox.getSelectedIndex());
						if(yearText.isEmpty() || location.isEmpty() || remark.isEmpty() || descText.isEmpty() || materialSelected.isEmpty() || categorySelected.isEmpty() || ownerSelected.isEmpty() || collectSelected.isEmpty()) {
							JOptionPane.showMessageDialog(null,"No input can be left empty","Empty input",JOptionPane.ERROR_MESSAGE);
							return;
						}
						int materialId=dbMain.getEntryId("materialid","material","materialname",materialSelected);
						int categoryId=dbMain.getEntryId("categoryid","category","categoryname",categorySelected);
						int ownerId=dbMain.getEntryId("ownerid","owner","ownername",ownerSelected);
						int collectId=dbMain.getEntryId("collectid","collection","givenby",collectSelected);
						int year=Integer.parseInt(yearText);
						dbMain.insertArtifact.setInt(1,entryId);
						dbMain.insertArtifact.setString(2,entryName);
						dbMain.insertArtifact.setString(3,location);
						dbMain.insertArtifact.setString(4,remark);
						dbMain.insertArtifact.setString(5,descText);
						dbMain.insertArtifact.setInt(6,year);
						dbMain.insertArtifact.setInt(7,materialId);
						dbMain.insertArtifact.setInt(8,categoryId);
						dbMain.insertArtifact.setInt(9,ownerId);
						dbMain.insertArtifact.setInt(10,collectId);
						dbMain.insertArtifact.execute();
					}
					else if(materialRadio.isSelected()) {
						dbMain.insertMaterial.setInt(1,entryId);
						dbMain.insertMaterial.setString(2,entryName);
						dbMain.insertMaterial.execute();
					}
					else if(ownerRadio.isSelected()) {
						dbMain.insertOwner.setInt(1,entryId);
						dbMain.insertOwner.setString(2,entryName);
						dbMain.insertOwner.execute();
					}
					else if(categoryRadio.isSelected()) {
						dbMain.insertCategory.setInt(1,entryId);
						dbMain.insertCategory.setString(2,entryName);
						dbMain.insertCategory.execute();
					}
					else if(collectRadio.isSelected()) {
						if(yearText.isEmpty()) {
							JOptionPane.showMessageDialog(null,"No input can be left empty","Empty input",JOptionPane.ERROR_MESSAGE);
							return;
						}
						int year=Integer.parseInt(yearText);
						dbMain.insertCollection.setInt(1,entryId);
						dbMain.insertCollection.setString(2,entryName);
						dbMain.insertCollection.setInt(3,year);
						dbMain.insertCollection.execute();
					}
				}
				catch(SQLException sqle) {
					JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
				}
				JOptionPane.showMessageDialog(null,"Your record has been succesfully\nadded to the databse","Success",JOptionPane.INFORMATION_MESSAGE);
				clearAllInputs();
				insertFieldsFrame.dispose();
				insertOptions.dispose();
			}
		});
		insertOptions.add(insertLabel);
		insertOptions.add(radioPanel);
		insertOptions.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(artifactRadio.isSelected()) {
					artifactFrame();
				}
				else if(materialRadio.isSelected() || ownerRadio.isSelected() || categoryRadio.isSelected()) {
					genericFrame();
				}
				else if(collectRadio.isSelected()) {
					collectFrame();
				}
				insertOptions.setVisible(false);
			}
		});
		insertOptions.pack();
		insertOptions.setVisible(true);
		insertOptions.setResizable(false);
		insertOptions.setLocationRelativeTo(null);
	}
}
