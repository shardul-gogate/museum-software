package windowManager;

import javax.swing.*;
import java.awt.*;

public class Search {
	
	private JFrame searchOptions;
	
	private JPanel radioPanel;
	
	private BoxLayout frameLayout;
	
	private GridLayout radioPanelLayout;
	
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
		
		radioPanelLayout=new GridLayout(7,1);
		radioPanel=new JPanel();
		radioPanel.setLayout(radioPanelLayout);
		radioPanel.add(idRadio);
		radioPanel.add(nameRadio);
		radioPanel.add(locRadio);
		radioPanel.add(yearRadio);
		radioPanel.add(materialRadio);
		radioPanel.add(ownerRadio);
		radioPanel.add(collectRadio);
	}
	
	public Search() {
		setRadio();
		
		searchLabel=new JLabel("Seach By");
		
		queryField=new JTextField(20);
		
		submitButton=new JButton("Submit");
		
		searchOptions=new JFrame();
		frameLayout=new BoxLayout(searchOptions.getContentPane(),BoxLayout.Y_AXIS);
		searchOptions.setLayout(frameLayout);
		searchOptions.setVisible(true);
		searchOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchOptions.add(searchLabel);
		searchOptions.add(radioPanel);
		searchOptions.add(queryField);
		searchOptions.add(submitButton);
		searchOptions.pack();
		searchOptions.setResizable(false);
	}
}
