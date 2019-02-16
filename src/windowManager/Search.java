package windowManager;

import javax.swing.*;
import java.awt.event.*;

/**
 * this class constructs page of search options and results
 */
public class Search implements ActionListener{
	
	private JFrame searchOptions,searchResults;
	private JPanel radioPanel;
	private BoxLayout frameLayout,resultLayout,radioPanelLayout;
	private JRadioButton idRadio, nameRadio, yearRadio, locRadio, ownerRadio, collectRadio, materialRadio;
	private ButtonGroup radioGroup;
	private JLabel searchLabel;
	private JTextField queryField;
	private JButton submitButton,backButton;
	private JComboBox locationList;
	
	/**
	 * Constructs all the radio buttons and adds them to the panels
	 */
	private void setRadio() {
		idRadio=new JRadioButton("ID",true);
		idRadio.addActionListener(this);
		nameRadio=new JRadioButton("Name");
		nameRadio.addActionListener(this);
		yearRadio=new JRadioButton("Year");
		yearRadio.addActionListener(this);
		locRadio=new JRadioButton("Location");
		locRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				searchOptions.remove(queryField);
				searchOptions.remove(submitButton);
				searchOptions.add(locationList);
				searchOptions.add(submitButton);
				searchOptions.pack();
			}
		});
		ownerRadio=new JRadioButton("Owner");
		ownerRadio.addActionListener(this);
		collectRadio=new JRadioButton("Collection");
		collectRadio.addActionListener(this);
		materialRadio=new JRadioButton("Material");
		materialRadio.addActionListener(this);
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
	
	/**
	 * default constructor
	 * constructs frame and calls component methods
	 * adds action listener to search button to proceed to search results under appropriate circumstances
	 */
	public Search() {
		setRadio();
		searchLabel=new JLabel("Seach By");
		queryField=new JTextField(15);
		locationList=new JComboBox();
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
			}
		});
		searchOptions.pack();
		searchOptions.setResizable(false);
		searchOptions.setLocationRelativeTo(null);
	}
	
	/**
	 * displays the search results in tabular format
	 * @param radioSelected - is the action command of the selected radio button in the option
	 * @param queryString - is the actual content that is to be searched in the selected fields
	 */
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
	
	public void actionPerformed(ActionEvent ae)
	{
		searchOptions.remove(locationList);
		searchOptions.remove(submitButton);
		searchOptions.add(queryField);
		searchOptions.add(submitButton);
		searchOptions.pack();
	}
}
