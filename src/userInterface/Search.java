package userInterface;

import javax.swing.*;
import java.awt.*;

public class Search {
	
	private JFrame searchOptionsFrame;
	
	private GridLayout frameLayout;
	
	private JRadioButton idRadio, nameRadio, yearRadio, locRadio, ownerRadio, collectRadio, materialRadio;
	
	private ButtonGroup radioGroup;
	
	private JLabel searchLabel;
	
	private JTextField queryField;
	
	private JButton submitButton;
	
	private void setRadio() {
		idRadio=new JRadioButton("ID",true);
		nameRadio=new JRadioButton("Name");
		yearRadio=new JRadioButton("Year");
		locRadio=new JRadioButton("Location");
		ownerRadio=new JRadioButton("Owner");
		collectRadio=new JRadioButton("Collection");
		materialRadio=new JRadioButton("Material");
		
		radioGroup=new ButtonGroup();
		
		radioGroup.add(idRadio);
		radioGroup.add(nameRadio);
		radioGroup.add(locRadio);
		radioGroup.add(yearRadio);
		radioGroup.add(materialRadio);
		radioGroup.add(ownerRadio);
		radioGroup.add(collectRadio);
	}
	
	public Search() {
		setRadio();
		
		searchLabel=new JLabel("Seach By");
		
		queryField=new JTextField(20);
		
		submitButton=new JButton("Submit");
		
		frameLayout=new GridLayout(10,1,0,5);
		
		searchOptionsFrame=new JFrame();		
		searchOptionsFrame.setLayout(frameLayout);
		searchOptionsFrame.setVisible(true);
		searchOptionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchOptionsFrame.add(searchLabel);
		searchOptionsFrame.add(idRadio);
		searchOptionsFrame.add(nameRadio);
		searchOptionsFrame.add(locRadio);
		searchOptionsFrame.add(yearRadio);
		searchOptionsFrame.add(materialRadio);
		searchOptionsFrame.add(ownerRadio);
		searchOptionsFrame.add(collectRadio);
		searchOptionsFrame.add(queryField);
		searchOptionsFrame.add(submitButton);
		searchOptionsFrame.pack();
		
	}
}
