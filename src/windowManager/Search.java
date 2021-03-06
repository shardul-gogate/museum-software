package windowManager;

import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * this class constructs page of search options and results
 */
public class Search implements ActionListener{
	
	private Database dbMain;
	private JFrame searchOptions,searchResults;
	private JPanel radioPanel;
	private BoxLayout frameLayout,resultLayout,radioPanelLayout;
	private JRadioButton idRadio,nameRadio,yearRadio,locRadio,ownerRadio,collectRadio,materialRadio,categoryRadio;
	private ButtonGroup radioGroup;
	private JLabel searchLabel;
	private JTextField queryField;
	private JButton submitButton;
	private JComboBox<String> locationBox,collectBox,categoryBox,materialBox,ownerBox;
	private JTable artifactTable;
	private ResultSet allArtifacts;
	private Statement artifactStmt;
	
	private void constructLists() {
		Vector<String> listContents=new Vector<String>();
		try {
			dbMain.rs=dbMain.stmt.executeQuery("SELECT DISTINCT location FROM artifact");
			while(dbMain.rs.next()) {
				listContents.add(dbMain.rs.getString(1));
			}
			locationBox=new JComboBox<String>(listContents);
			listContents=dbMain.getAllEntries("materialName","material");
			materialBox=new JComboBox<String>(listContents);
			listContents=dbMain.getAllEntries("categoryName","category");
			categoryBox=new JComboBox<String>(listContents);
			listContents=dbMain.getAllEntries("ownerName","owner");
			ownerBox=new JComboBox<String>(listContents);
			listContents=dbMain.getAllEntries("givenBy","collection");
			collectBox=new JComboBox<String>(listContents);
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Constructs all the radio buttons and adds them to the panels
	 */
	private void setRadio() {
		idRadio=new JRadioButton("ID",true);
		idRadio.addActionListener(this);
		nameRadio=new JRadioButton("Name");
		nameRadio.addActionListener(this);
		yearRadio=new JRadioButton("Year");
		yearRadio.addActionListener(this);
		locRadio=new JRadioButton("Location");
		locRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				boolean selected=true;
				if(ie.getStateChange()!=1) {
					selected=false;
				}
				if(selected) {
					searchOptions.remove(queryField);
					searchOptions.remove(submitButton);
					searchOptions.add(locationBox);
					searchOptions.add(submitButton);
					searchOptions.pack();
				}
				else {
					searchOptions.remove(locationBox);
				}
			}
		});
		ownerRadio=new JRadioButton("Owner");
		ownerRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				boolean selected=true;
				if(ie.getStateChange()!=1) {
					selected=false;
				}
				if(selected) {
					searchOptions.remove(queryField);
					searchOptions.remove(submitButton);
					searchOptions.add(ownerBox);
					searchOptions.add(submitButton);
					searchOptions.pack();
				}
				else {
					searchOptions.remove(ownerBox);
				}
			}
		});
		collectRadio=new JRadioButton("Collection");
		collectRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				boolean selected=true;
				if(ie.getStateChange()!=1) {
					selected=false;
				}
				if(selected) {
					searchOptions.remove(queryField);
					searchOptions.remove(submitButton);
					searchOptions.add(collectBox);
					searchOptions.add(submitButton);
					searchOptions.pack();
				}
				else {
					searchOptions.remove(collectBox);
				}
			}
		});
		materialRadio=new JRadioButton("Material");
		materialRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				boolean selected=true;
				if(ie.getStateChange()!=1) {
					selected=false;
				}
				if(selected) {
					searchOptions.remove(queryField);
					searchOptions.remove(submitButton);
					searchOptions.add(materialBox);
					searchOptions.add(submitButton);
					searchOptions.pack();
				}
				else {
					searchOptions.remove(materialBox);
				}
			}
		});
		categoryRadio=new JRadioButton("Category");
		categoryRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				boolean selected=true;
				if(ie.getStateChange()!=1) {
					selected=false;
				}
				if(selected) {
					searchOptions.remove(queryField);
					searchOptions.remove(submitButton);
					searchOptions.add(categoryBox);
					searchOptions.add(submitButton);
					searchOptions.pack();
				}
				else {
					searchOptions.remove(categoryBox);
				}
			}
		});
		radioGroup=new ButtonGroup();
		radioGroup.add(idRadio);
		radioGroup.add(nameRadio);
		radioGroup.add(locRadio);
		radioGroup.add(yearRadio);
		radioGroup.add(materialRadio);
		radioGroup.add(ownerRadio);
		radioGroup.add(collectRadio);
		radioGroup.add(categoryRadio);
		radioPanel=new JPanel();
		radioPanelLayout=new BoxLayout(radioPanel,BoxLayout.Y_AXIS);
		radioPanel.setLayout(radioPanelLayout);
		radioPanel.add(idRadio);
		radioPanel.add(nameRadio);
		radioPanel.add(locRadio);
		radioPanel.add(yearRadio);
		radioPanel.add(categoryRadio);
		radioPanel.add(materialRadio);
		radioPanel.add(ownerRadio);
		radioPanel.add(collectRadio);
	}
	
	private void constructResult(String queryString) {
		allArtifacts=null;
		try {
			artifactStmt=dbMain.conn.createStatement();
			if(idRadio.isSelected()) {
				int artifactId=Integer.parseInt(queryString);
				allArtifacts=artifactStmt.executeQuery("SELECT * FROM artifact where artifactid=" + artifactId);
			}
			else if(nameRadio.isSelected()) {
				allArtifacts=artifactStmt.executeQuery("SELECT * FROM artifact where artifactname='" + queryString +"'");
			}
			else if(yearRadio.isSelected()) {
				int year=Integer.parseInt(queryString);
				allArtifacts=artifactStmt.executeQuery("SELECT * FROM artifact where year=" + year);
			}
			else if(locRadio.isSelected()) {
				allArtifacts=artifactStmt.executeQuery("SELECT * FROM artifact where location='" + queryString +"'");
			}
			else if(materialRadio.isSelected() ) {
				int materialId=dbMain.getEntryId("materialid","material","materialname",queryString);
				allArtifacts=artifactStmt.executeQuery("SELECT * FROM artifact where materialid=" + materialId);
			}
			else if(ownerRadio.isSelected()) {
				int ownerId=dbMain.getEntryId("ownerid","owner","ownername",queryString);
				allArtifacts=artifactStmt.executeQuery("SELECT * FROM artifact where ownerid=" + ownerId);
			}
			else if(categoryRadio.isSelected()) {
				int categoryId=dbMain.getEntryId("categoryid","category","categoryname",queryString);
				allArtifacts=artifactStmt.executeQuery("SELECT * FROM artifact where categoryid=" + categoryId);
			}
			else if(collectRadio.isSelected()) {
				int collectId=dbMain.getEntryId("collectid","collection","givenby",queryString);
				allArtifacts=artifactStmt.executeQuery("SELECT * FROM artifact where collectid=" + collectId);
			}
			Vector<Vector<String>> artifactData=new Vector<Vector<String>>();
			Vector<String> colomnName=new Vector<String>();
			dbMain.rsmd=allArtifacts.getMetaData();
			int colomnCount=dbMain.rsmd.getColumnCount();
			for(int i=1;i<=colomnCount;i++) {
				String currentColomnName=dbMain.rsmd.getColumnName(i);
				colomnName.add(currentColomnName);
			}
			artifactData.add(colomnName);
			while(allArtifacts.next()) {
				Vector<String> rowData=new Vector<String>();
				String artifactId=Integer.toString(allArtifacts.getInt(1));
				rowData.add(artifactId);
				String artifactName=allArtifacts.getString(2);
				rowData.add(artifactName);
				String location=allArtifacts.getString(3);
				rowData.add(location);
				String remark=allArtifacts.getString(4);
				rowData.add(remark);
				String description=allArtifacts.getString(5);
				rowData.add(description);
				String year=Integer.toString(allArtifacts.getInt(6));
				rowData.add(year);
				String materialName=dbMain.getEntryName("materialname","material","materialid",allArtifacts.getInt(7));
				rowData.add(materialName);
				String categoryName=dbMain.getEntryName("categoryname","category","categoryid",allArtifacts.getInt(8));
				rowData.add(categoryName);
				String ownerName=dbMain.getEntryName("ownername","owner","ownerid",allArtifacts.getInt(9));
				rowData.add(ownerName);
				String collection=dbMain.getEntryName("givenby","collection","collectid",allArtifacts.getInt(10));
				rowData.add(collection);
				artifactData.add(rowData);
			}
			artifactTable=new JTable(artifactData,colomnName);
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void showResult() {
		searchResults=new JFrame("Search Results");
		resultLayout= new BoxLayout(searchResults.getContentPane(),BoxLayout.Y_AXIS);
		searchResults.setLayout(resultLayout);
		searchResults.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchResults.setVisible(true);
		searchResults.add(artifactTable);
		searchResults.pack();
		searchResults.setResizable(false);
		searchResults.setLocationRelativeTo(null);
	}
	
	/**
	 * default constructor
	 * constructs frame and calls component methods
	 * adds action listener to search button to proceed to search results under appropriate circumstances
	 */
	public Search(Database dbMain) {
		this.dbMain=dbMain;
		constructLists();
		setRadio();
		searchLabel=new JLabel("Seach By");
		queryField=new JTextField(15);
		submitButton=new JButton("Submit");
		searchOptions=new JFrame("Searh Options");
		frameLayout=new BoxLayout(searchOptions.getContentPane(),BoxLayout.Y_AXIS);
		searchOptions.setLayout(frameLayout);
		searchOptions.setVisible(true);
		searchOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchOptions.add(searchLabel);
		searchOptions.add(radioPanel);
		searchOptions.add(queryField);
		searchOptions.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String queryString=null;
				if(idRadio.isSelected() || yearRadio.isSelected() || nameRadio.isSelected()) {
					queryString=queryField.getText();
					if(queryString.isEmpty()) {
						JOptionPane.showMessageDialog(searchOptions,"Search input field is empty","No input",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				else if(locRadio.isSelected()) {
					queryString=locationBox.getItemAt(locationBox.getSelectedIndex());
				}
				else if(materialRadio.isSelected() ){
					queryString=materialBox.getItemAt(materialBox.getSelectedIndex());
				}
				else if(ownerRadio.isSelected()) {
					queryString=ownerBox.getItemAt(ownerBox.getSelectedIndex());
				}
				else if(categoryRadio.isSelected()) {
					queryString=categoryBox.getItemAt(categoryBox.getSelectedIndex());
				}
				else if(collectRadio.isSelected()) {
					queryString=collectBox.getItemAt(collectBox.getSelectedIndex());
				}
				constructResult(queryString);
				showResult();
			}
		});
		searchOptions.pack();
		searchOptions.setResizable(false);
		searchOptions.setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		searchOptions.remove(submitButton);
		searchOptions.add(queryField);
		searchOptions.add(submitButton);
		searchOptions.pack();
	}
}
