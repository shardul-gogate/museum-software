package windowManager;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
	private DefaultTableModel tabModel;
	private ResultSet allArtifacts;
	private Statement artifactStmt;
	private JMenuBar menuBar;
	private JMenu reportMenu,tabOptMenu,subMenu;
	private JMenuItem locRep,catRep,matRep,artRep,refreshTab,artTab,catTab,matTab;
	private String currTab;
	
	/**
	 * this method constructs all the buttons and adds listeners
	 */
	private void setButtons() {
		searchButton=new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Search(dbMain);
			}
		});
		insertButton=new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Insert(dbMain);
			}
		});;
		deleteButton=new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Delete(dbMain);
			}
		});;
		updateButton=new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Update(dbMain);
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
	
	private void addMenuBar() {
		menuBar=new JMenuBar();
		reportMenu=new JMenu("Report");
		locRep=new JMenuItem("Location");
		catRep=new JMenuItem("Catagory");
		matRep=new JMenuItem("Material");
		matRep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Report(dbMain,2);				
			}
		});
		artRep=new JMenuItem("Artifact");
		artRep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Report(dbMain,1);
			}
		});
		reportMenu.add(locRep);
		reportMenu.add(catRep);
		reportMenu.add(matRep);
		reportMenu.add(artRep);
		menuBar.add(reportMenu);
		tabOptMenu=new JMenu("Options");
		subMenu=new JMenu("Display Table");
		artTab=new JMenuItem("Artifact");
		artTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(!currTab.equals("art")) {
					tabModel=(DefaultTableModel)artifactTable.getModel();
					tabModel.setRowCount(0);
					constructArtifactTable();
					homePageFrame.add(artifactTable);
					homePageFrame.add(buttonPanel);
					homePageFrame.pack();
				}
			}
		});
		catTab=new JMenuItem("Category");
		catTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(!currTab.equals("cat")) {
					tabModel=(DefaultTableModel)artifactTable.getModel();
					tabModel.setRowCount(0);
					constructCategoryTable();
					homePageFrame.add(artifactTable);
					homePageFrame.add(buttonPanel);
					homePageFrame.pack();
				}
			}
		});
		matTab=new JMenuItem("Material");
		matTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(!currTab.equals("mat")) {
					tabModel=(DefaultTableModel)artifactTable.getModel();
					tabModel.setRowCount(0);
					constructMaterialTable();
					homePageFrame.add(artifactTable);
					homePageFrame.add(buttonPanel);
					homePageFrame.pack();
				}
			}
		});
		subMenu.add(artTab);
		subMenu.add(catTab);
		subMenu.add(matTab);
		tabOptMenu.add(subMenu);
		tabOptMenu.addSeparator();
		refreshTab=new JMenuItem("Refresh table");
		tabOptMenu.add(refreshTab);
		refreshTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tabModel=(DefaultTableModel)artifactTable.getModel();
				tabModel.setRowCount(0);
				if(currTab.equals("art")) {
					constructArtifactTable();
				}
				else if(currTab.equals("cat")) {
					constructCategoryTable();
				}
				else if(currTab.equals("mat")) {
					constructMaterialTable();
				}
				homePageFrame.add(artifactTable);
				homePageFrame.add(buttonPanel);
				homePageFrame.pack();
			}
		});
		menuBar.add(tabOptMenu);
		homePageFrame.setJMenuBar(menuBar);
	}
	
	private void constructCategoryTable() {
		Vector<Vector<String>> categoryData=new Vector<Vector<String>>();
		Vector<String> colomnName=new Vector<String>();
		try {
			artifactStmt=dbMain.conn.createStatement();
			allArtifacts=artifactStmt.executeQuery("SELECT * FROM category;");
			dbMain.rsmd=allArtifacts.getMetaData();
			int colomnCount=dbMain.rsmd.getColumnCount();
			for(int i=1;i<=colomnCount;i++) {
				String currentColomnName=dbMain.rsmd.getColumnName(i);
				colomnName.add(currentColomnName);
			}
			categoryData.add(colomnName);
			while(allArtifacts.next()) {
				Vector<String> rowData=new Vector<String>();
				String categoryId=Integer.toString(allArtifacts.getInt(1));
				rowData.add(categoryId);
				String categoryName=allArtifacts.getString(2);
				rowData.add(categoryName);
				categoryData.add(rowData);
			}
			artifactTable=new JTable(categoryData,colomnName);
			currTab="cat";
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void constructMaterialTable() {
		Vector<Vector<String>> materialData=new Vector<Vector<String>>();
		Vector<String> colomnName=new Vector<String>();
		try {
			artifactStmt=dbMain.conn.createStatement();
			allArtifacts=artifactStmt.executeQuery("SELECT * FROM material;");
			dbMain.rsmd=allArtifacts.getMetaData();
			int colomnCount=dbMain.rsmd.getColumnCount();
			for(int i=1;i<=colomnCount;i++) {
				String currentColomnName=dbMain.rsmd.getColumnName(i);
				colomnName.add(currentColomnName);
			}
			materialData.add(colomnName);
			while(allArtifacts.next()) {
				Vector<String> rowData=new Vector<String>();
				String materialId=Integer.toString(allArtifacts.getInt(1));
				rowData.add(materialId);
				String materialName=allArtifacts.getString(2);
				rowData.add(materialName);
				materialData.add(rowData);
			}
			artifactTable=new JTable(materialData,colomnName);
			currTab="mat";
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
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
			//JTable constTable=new JTable(artifactData,colomnName);
			artifactTable=new JTable(artifactData,colomnName);
			currTab="art";
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
		currTab="art";
		setButtons();
		setPanel();
		homePageFrame=new JFrame("Home Page");
		frameLayout=new BoxLayout(homePageFrame.getContentPane(),BoxLayout.Y_AXIS);
		homePageFrame.setLayout(frameLayout);
		homePageFrame.setVisible(true);
		addMenuBar();
		constructArtifactTable();
		homePageFrame.add(artifactTable);
		homePageFrame.add(buttonPanel);
		homePageFrame.pack();
		homePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homePageFrame.setResizable(false);
		homePageFrame.setLocationRelativeTo(null);
	}
}
