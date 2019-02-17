package windowManager;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.Statement;

/**
 * Home-page class of the software
 * Displays all present artifacts in the table
 * Displays options for further insertion, deletion and searches
 */
public class HomePage {
	
	private JFrame homePageFrame;
	private Database dbMain;
	private GridLayout buttonLayout;
	private BoxLayout frameLayout;
	private JButton searchButton, insertButton, deleteButton, updateButton;
	private JPanel buttonPanel;
	private JTable artifactTable;
	private ResultSet allArtifacts;
	private Statement artifactStmt;
	
	/**
	 * this method constructs all the buttons and adds listeners
	 */
	private void setButtons() {
		searchButton=new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Search(dbMain);
				constructArtifactTable();
				homePageFrame.add(artifactTable);
				homePageFrame.add(buttonPanel);
			}
		});
		insertButton=new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Insert(dbMain);
				constructArtifactTable();
				homePageFrame.add(artifactTable);
				homePageFrame.add(buttonPanel);
			}
		});;
		deleteButton=new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Delete(dbMain);
				constructArtifactTable();
				homePageFrame.add(artifactTable);
				homePageFrame.add(buttonPanel);
			}
		});;
		updateButton=new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Update(dbMain);
				constructArtifactTable();
				homePageFrame.add(artifactTable);
				homePageFrame.add(buttonPanel);
			}
		});;
	}
	
	/**
	 * this method constructs the panels and adds all the necessary components to them
	 */
	private void setPanel() {
		buttonLayout=new GridLayout(1,4);
		buttonPanel=new JPanel();
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.add(searchButton);
		buttonPanel.add(insertButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
	}
	
	private void constructArtifactTable() {
		Vector<Vector<String>> artifactData=new Vector<Vector<String>>();
		Vector<String> colomnName=new Vector<String>();
		try {
			artifactStmt=dbMain.conn.createStatement();
			allArtifacts=artifactStmt.executeQuery("SELECT * FROM artifact;");
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
				String remark=allArtifacts.getString(4);
				rowData.add(remark);
				String location=allArtifacts.getString(3);
				rowData.add(location);
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
	
	/**
	 * the default constructor calls the methods for components and constructs the frame
	 */
	public HomePage(Database dbMain) {
		this.dbMain=dbMain;
		allArtifacts=null;
		setButtons();
		setPanel();
		homePageFrame=new JFrame("Home Page");
		frameLayout=new BoxLayout(homePageFrame.getContentPane(),BoxLayout.Y_AXIS);
		homePageFrame.setLayout(frameLayout);
		homePageFrame.setVisible(true);
		constructArtifactTable();
		homePageFrame.add(artifactTable);
		homePageFrame.add(buttonPanel);
		homePageFrame.pack();
		homePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homePageFrame.setResizable(false);
		homePageFrame.setLocationRelativeTo(null);
	}
}
