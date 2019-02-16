package windowManager;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * Home-page class of the software
 * Displays all present artifacts in the table
 * Displays options for further insertion, deletion and searches
 */
public class HomePage {
	private JTable t;
	private JFrame homePageFrame;
	private GridLayout labelLayout, buttonLayout;
	private BoxLayout frameLayout;
	private JButton searchButton, insertButton, deleteButton, updateButton;
	private JLabel idLabel, nameLabel, descLabel, locLabel, remarkLabel, yearLabel, collectLabel, ownerLabel, categoryLabel, materialLabel;
	private JPanel labelPanel, buttonPanel;
	private Connection conn;
	
	/**
	 * this method constructs all the buttons and adds listeners
	 */
	private void setButtons() {
		searchButton=new JButton("Search");
		searchButton.setBounds(330,265,150,50);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Search();
			}
		});
		insertButton=new JButton("Insert");
		insertButton.setBounds(330,265,150,50);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Insert();
			}
		});;
		deleteButton=new JButton("Delete");
		deleteButton.setBounds(330,265,150,50);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Delete();
			}
		});;
		updateButton=new JButton("Update");
		updateButton.setBounds(330,265,150,50);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new Update();
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
		/*labelLayout=new GridLayout(1,9);
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
		labelPanel.add(ownerLabel);*/
		buttonLayout=new GridLayout(1,4);
		buttonPanel=new JPanel();
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.add(searchButton);
		buttonPanel.add(insertButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
	}
	
	/*private void connectDb() { 
		try{
			conn=null;
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost","postgres","onkar@123");
			if(conn==null) {
				JOptionPane.showMessageDialog(null,"Error in connection","No connection",JOptionPane.ERROR_MESSAGE);
				return;
			}
			System.out.println("Connection Successful!!");
		}catch(Exception e) {}
	}*/
	
	/**
	 * the default constructor calls the methods for components and constructs the frame
	 */
	public HomePage() {
		//setLabels();
		setButtons();
		setPanel();
		homePageFrame=new JFrame("Home Page");
		frameLayout=new BoxLayout(homePageFrame.getContentPane(),BoxLayout.Y_AXIS);
		homePageFrame.setLayout(frameLayout);
		homePageFrame.setSize(1000,658);
		homePageFrame.setVisible(true);
		//homePageFrame.add(labelPanel);
		//JTextField test=new JTextField();
		//homePageFrame.add(test);
		//homePageFrame.add(buttonPanel);
		homePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//homePageFrame.pack();
		homePageFrame.setResizable(true);
		//0homePageFrame.setLocationRelativeTo(null);
		
		try{
			conn=null;
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","onkar@123");
			if(conn==null) {
				JOptionPane.showMessageDialog(null,"Error in connection","No connection",JOptionPane.ERROR_MESSAGE);
				return;
			}
		//test.setText("Connection Successful!!");
		//labelPanel.add(test);
			//System.out.println("Connection Succesful");
		}catch(Exception e) {}
		
		//connectDb();
		ResultSet rs=null;
		try{
		Statement stmt=conn.createStatement();
		rs=stmt.executeQuery("Select * from Artifact");
		ResultSetMetaData rsmd=rs.getMetaData();
		
		Vector colname=new Vector();
		int cols=rsmd.getColumnCount();
		for(int i=1;i<=cols;i++) {
			colname.add(rsmd.getColumnName(i));
		}
		
		Vector dta=new Vector();
	      while(rs.next())
	      {
	         Vector vec=new Vector();
	     
		  for(int j=1;j<=cols;j++)
		  {
		     vec.add(rs.getObject(j));
		  }
	          dta.add(vec);
	       }
	       t=new JTable(dta,colname);
	       homePageFrame.add(t);
	       homePageFrame.add(buttonPanel);
	       homePageFrame.pack();
	       //homePageFrame.setSize(1500,700);
	       conn.close();
		/*if(rs==null)
		{
			test.setText("Notworking");
		}*/
		/*while(rs.next()) {
			//System.out.println(rs.getString(2));
			//test.setText(rs.getString(1));
		}*/
		}catch(Exception e) {}
		
	}
}
