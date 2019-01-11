package windowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Search {
	
	private JFrame searchOptions,searchResults;
	private JPanel radioPanel;
	private BoxLayout frameLayout,resultLayout,radioPanelLayout;
	private JRadioButton idRadio, nameRadio, yearRadio, locRadio, ownerRadio, collectRadio, materialRadio;
	private ButtonGroup radioGroup;
	private JLabel searchLabel;
	private JTextField queryField;
	private JButton submitButton,backButton;
	
	private String getSelectedRadio() {
		String radioSelected="";
		if(idRadio.isSelected()) radioSelected="id";
		else if(nameRadio.isSelected()) radioSelected="name";
		else if(yearRadio.isSelected()) radioSelected="year";
		else if(locRadio.isSelected()) radioSelected="location";
		else if(ownerRadio.isSelected()) radioSelected="owner";
		else if(collectRadio.isSelected()) radioSelected="collection";
		else if(materialRadio.isSelected()) radioSelected="material";		
		return radioSelected;
	}
	
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
		radioPanel=new JPanel();
		radioPanelLayout=new BoxLayout(radioPanel,BoxLayout.Y_AXIS);
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
				String queryString=queryField.getText();
				if(queryString.isEmpty()) {
					JOptionPane.showMessageDialog(searchOptions,"Search input field is empty","No input",JOptionPane.ERROR_MESSAGE);
					return;
				}
				String radioSelected=getSelectedRadio();
				if(radioSelected.isEmpty()) {
					JOptionPane.showMessageDialog(searchOptions,"None of the categories are selected","No selection",JOptionPane.ERROR_MESSAGE);
					return;
				}
				searchOptions.setVisible(false);
				searchResults(radioSelected,queryString);
			}
		});
		searchOptions.pack();
		searchOptions.setResizable(false);
		searchOptions.setLocationRelativeTo(null);
	}
	
	private void searchResults(String radioSelected,String queryString) {
		searchResults=new JFrame("Search Results");
		resultLayout= new BoxLayout(searchResults.getContentPane(),BoxLayout.Y_AXIS);
		searchResults.setLayout(resultLayout);
		searchResults.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		searchResults.setVisible(true);
		backButton=new JButton("Back to options");
		searchResults.add(new JTextArea(10,10));
		searchResults.add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				searchResults.dispose();
				searchOptions.setVisible(true);
			}
		});;
		searchResults.pack();
		searchResults.setResizable(false);
		searchResults.setLocationRelativeTo(null);
	}
}
