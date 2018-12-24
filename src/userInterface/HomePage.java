package userInterface;

import java.awt.*;
import javax.swing.*;

public class HomePage {
	
	public JFrame homePageFrame;
	
	private GridLayout frameLayout;
	
	private JButton searchButton, insertButton, deleteButton, updateButton;
	
	private JLabel idLabel, nameLabel, descLabel, locLabel, remarkLabel, yearLabel, collectLabel, ownerLabel, categoryLabel;
	
	private JPanel labelPanel, buttonPanel;
	
	private void setButtons() {
		searchButton=new JButton("Search");
		insertButton=new JButton("Insert");
		deleteButton=new JButton("Delete");
		updateButton=new JButton("Update");
	}
	
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
	}
	
	private void setPanel() {
		
	}
	
	public HomePage() {
		homePageFrame=new JFrame();
		frameLayout=new GridLayout(3,1,0,5);
		labelPanel=new JPanel();
		labelPanel.setLayout(new GridLayout(1,9));
		buttonPanel=new JPanel();
		buttonPanel.setLayout(new GridLayout(1,4));
		setButtons();
		setLabels();
		setPanel();
	}
	
}
