package windowManager;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Still under construction
 * Class for page of Inserting the artifacts
 */
public class Insert {
	private JRadioButton artifactRadio,materialRadio,categoryRadio,collectRadio,ownerRadio;
	private ButtonGroup radioGroup;
	private JButton submitButton,insertButton;
	private JPanel radioPanel,fieldsPanel;
	private JFrame insertOptions,insertFieldsFrame;
	private BoxLayout optionsLayout,fieldsLayout,radioLayout;
	private GridLayout fieldsPanelLayout;
	private JLabel insertLabel;
	private JTextField nameField,yearField,locField,remarkField;
	private JTextArea desciption;
	private JComboBox collectBox,categoryBox,materialBox,OwnerBox;
	private JLabel nameLabel,yearLabel,locLabel,remarkLabel,descriptionLabel,collectLabel,ownerLabel,materialLabel,categoryLabel;
	
	/**
	 * @return returns the action command of the radio button
	 */
	private String getSelectedRadio() {
		if(artifactRadio.isSelected()) return "location";
		else if(ownerRadio.isSelected()) return "owner";
		else if(collectRadio.isSelected()) return "collection";
		else if(materialRadio.isSelected()) return "material";
		else if(categoryRadio.isSelected()) return "category";
		else return "";
	}
	
	/**
	 * method that constructs radio buttons and panels
	 */
	private void setRadio() {
		radioPanel=new JPanel();
		radioLayout=new BoxLayout(radioPanel,BoxLayout.Y_AXIS);
		radioPanel.setLayout(radioLayout);
		radioGroup=new ButtonGroup();
		artifactRadio=new JRadioButton("Artifact",true);
		radioGroup.add(artifactRadio);
		materialRadio=new JRadioButton("Material");
		radioGroup.add(materialRadio);
		categoryRadio=new JRadioButton("Category");
		radioGroup.add(categoryRadio);
		collectRadio=new JRadioButton("Collection");
		radioGroup.add(collectRadio);
		ownerRadio=new JRadioButton("Owner");
		radioGroup.add(ownerRadio);
		radioPanel.add(artifactRadio);
		radioPanel.add(categoryRadio);
		radioPanel.add(materialRadio);
		radioPanel.add(collectRadio);
		radioPanel.add(ownerRadio);
	}
	
	/**
	 * this method is invoked if the user wants to insert and artifact
	 */
	private void insertArtifact() {
		insertFieldsFrame=new JFrame("Insert Artifact details");
		fieldsLayout=new BoxLayout(insertFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		insertFieldsFrame.setLayout(fieldsLayout);
		fieldsPanel=new JPanel();
		fieldsPanelLayout=new GridLayout(4,2);
		fieldsPanel.setLayout(fieldsPanelLayout);
		nameLabel=new JLabel("Name");
		fieldsPanel.add(nameLabel);
		nameField=new JTextField(20);
		fieldsPanel.add(nameField);
		yearLabel=new JLabel("Year");
		fieldsPanel.add(yearLabel);
		yearField=new JTextField(20);
		fieldsPanel.add(yearField);
		locLabel=new JLabel("Location");
		fieldsPanel.add(locLabel);
		locField=new JTextField(20);
		fieldsPanel.add(locField);
		remarkLabel=new JLabel("Remark");
		fieldsPanel.add(remarkLabel);
		remarkField=new JTextField(20);
		fieldsPanel.add(remarkField);
		
		insertFieldsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		insertFieldsFrame.pack();
		insertFieldsFrame.setVisible(true);
		insertFieldsFrame.setLocationRelativeTo(null);
		insertFieldsFrame.setResizable(false);
	}
	
	/**
	 * default constructor
	 * constructs frame
	 * calls different methods according to what user wants to insert
	 */
	public Insert() {
		setRadio();
		insertLabel=new JLabel("Insert");
		insertOptions=new JFrame("Insert Options");
		optionsLayout=new BoxLayout(insertOptions.getContentPane(),BoxLayout.Y_AXIS);
		insertOptions.setLayout(optionsLayout);
		submitButton=new JButton("Submit");
		insertOptions.add(insertLabel);
		insertOptions.add(radioPanel);
		insertOptions.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String radioSelected=getSelectedRadio();
				if(radioSelected.isEmpty()) {
					JOptionPane.showMessageDialog(insertOptions,"None of the categories are selected","No selection",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(radioSelected=="artifact") insertArtifact();
			}
		});
		insertOptions.pack();
		insertOptions.setVisible(true);
		insertOptions.setResizable(false);
		insertOptions.setLocationRelativeTo(null);
	}
}
