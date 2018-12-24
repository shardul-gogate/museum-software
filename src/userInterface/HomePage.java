package userInterface;

import java.awt.*;
import javax.swing.*;

public class HomePage {
	
	public JFrame homePageFrame;
	
	private GridLayout frameLayout;
	
	private JButton searchButton, insertButton, deleteButton, updateButton;
	
	private JLabel idLabel, nameLabel, descLabel, locLabel, remLabel, yearLabel, collectLabel, ownLabel, catLabel;
	
	private void setButtons() {
		searchButton=new JButton("Search");
		insertButton=new JButton("Insert");
	}
	
	private void setLabels() {
		
	}
	
	public HomePage() {
		homePageFrame=new JFrame();
		frameLayout=new GridLayout(3,1,0,5);
		setButtons();
		setLabels();
	}
	
}
