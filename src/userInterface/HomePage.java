package userInterface;

import java.awt.*;
import javax.swing.*;

public class HomePage {
	
	public JFrame homePageFrame;
	
	private GridLayout frameLayout, labelLayout, buttonLayout;
	
	private JButton searchButton, insertButton, deleteButton, updateButton;
	
	private JLabel idLabel, nameLabel, descLabel, locLabel, remarkLabel, yearLabel, collectLabel, ownerLabel, categoryLabel, materialLabel;
	
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
		materialLabel=new JLabel("Material");
	}
	
	private void setPanel() {
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
		
		buttonPanel.add(searchButton);
		buttonPanel.add(insertButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
	}
	
	public HomePage() {		
		setLabels();
		setButtons();
		
		labelLayout=new GridLayout(1,9,5,0);
		labelPanel=new JPanel();
		labelPanel.setLayout(labelLayout);
		
		buttonLayout=new GridLayout(1,4,5,0);
		buttonPanel=new JPanel();
		buttonPanel.setLayout(buttonLayout);
		
		setPanel();
		
		homePageFrame=new JFrame();
		frameLayout=new GridLayout(3,1,0,5);
		homePageFrame.setLayout(frameLayout);
		homePageFrame.setVisible(true);
		homePageFrame.add(labelPanel);
		homePageFrame.add(buttonPanel);
		homePageFrame.pack();
		homePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*public static void main(String args[]) {
		new HomePage();
	}*/
}
