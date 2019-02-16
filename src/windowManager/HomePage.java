package windowManager;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Home-page class of the software
 * Displays all present artifacts in the table
 * Displays options for further insertion, deletion and searches
 */
public class HomePage {
	
	private JFrame homePageFrame;
	private Database dbMain;
	private GridLayout labelLayout, buttonLayout;
	private BoxLayout frameLayout;
	private JButton searchButton, insertButton, deleteButton, updateButton;
	private JLabel idLabel, nameLabel, descLabel, locLabel, remarkLabel, yearLabel, collectLabel, ownerLabel, categoryLabel, materialLabel;
	private JPanel labelPanel, buttonPanel;
	private JTable artifactTable;
	
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
	 * this method constructs all the labels
	 */
	private void setLabels() {
		idLabel=new JLabel("ID");
		nameLabel=new JLabel("Name");
		descLabel=new JLabel("Description");
		locLabel=new JLabel("Location");
		remarkLabel=new JLabel("Remark");
		yearLabel=new JLabel("Year");
		collectLabel=new JLabel("Collection");
		ownerLabel=new JLabel("Owner");
		categoryLabel=new JLabel("Category");
		materialLabel=new JLabel("Material");
	}
	
	/**
	 * this method constructs the panels and adds all the necessary components to them
	 */
	private void setPanel() {
		labelLayout=new GridLayout(1,9);
		labelPanel=new JPanel();
		labelPanel.setLayout(labelLayout);
		labelPanel.add(idLabel);
		labelPanel.add(nameLabel);
		labelPanel.add(descLabel);
		labelPanel.add(locLabel);
		labelPanel.add(remarkLabel);
		labelPanel.add(materialLabel);
		labelPanel.add(yearLabel);
		labelPanel.add(categoryLabel);
		labelPanel.add(collectLabel);
		labelPanel.add(ownerLabel);
		buttonLayout=new GridLayout(1,4);
		buttonPanel=new JPanel();
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.add(searchButton);
		buttonPanel.add(insertButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
	}
	
	private void constructArtifactTable() {
		try {
			int colomnCount=dbMain.rsmd.getColumnCount();
		}
		catch(SQLException sqle) {
			dbMain.SQLExceptionMessage();
		}
		Vector artifactData=new Vector();
		Vector rowData=new Vector();
	}
	
	/**
	 * the default constructor calls the methods for components and constructs the frame
	 */
	public HomePage(Database dbMain) {
		this.dbMain=dbMain;
		setLabels();
		setButtons();
		setPanel();
		homePageFrame=new JFrame("Home Page");
		frameLayout=new BoxLayout(homePageFrame.getContentPane(),BoxLayout.Y_AXIS);
		homePageFrame.setLayout(frameLayout);
		homePageFrame.setVisible(true);
		homePageFrame.add(labelPanel);
		constructArtifactTable();
		homePageFrame.add(artifactTable);
		homePageFrame.add(buttonPanel);
		homePageFrame.pack();
		homePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homePageFrame.setResizable(false);
		homePageFrame.setLocationRelativeTo(null);
	}
}
