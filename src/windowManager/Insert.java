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
	private BoxLayout optionsLayout,fieldsFrameLayout,radioLayout;
	private GridLayout fieldsPanelLayout;
	private JTextField idField,nameField,yearField,locField,remarkField;
	private JTextArea description;
	private JComboBox collectBox,categoryBox,materialBox,OwnerBox;
	private JLabel idLabel,nameLabel,yearLabel,locLabel,remarkLabel,descLabel,collectLabel,ownerLabel,materialLabel,categoryLabel,insertLabel;
	
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
	
	private void setLabels() {
		nameLabel=new JLabel("Name");
		yearLabel=new JLabel("Year");
		locLabel=new JLabel("Location");
		remarkLabel=new JLabel("Remark");
		descLabel=new JLabel("Description");
		collectLabel=new JLabel("Collection");
		categoryLabel=new JLabel("Category");
		materialLabel=new JLabel("Material");
		ownerLabel=new JLabel("Owner");
		idLabel=new JLabel("ID");
	}
	
	private void setFields() {
		idField=new JTextField(20);
		nameField=new JTextField(20);
		remarkField=new JTextField(20);
		locField=new JTextField(20);
		description=new JTextArea(5,20);
	}
	
	/**
	 * this method is invoked if the user wants to insert an artifact
	 */
	private void insertFrame() {
		insertFieldsFrame=new JFrame("Insert");
		
		insertFieldsFrame.pack();
		insertFieldsFrame.setVisible(true);
		insertFieldsFrame.setResizable(false);
		insertFieldsFrame.setLocationRelativeTo(null);
	}
	
	private void genericFrame() {
		insertFieldsFrame=new JFrame();
		
		if(materialRadio.isSelected()) insertFieldsFrame.setTitle("Material");
		else if(ownerRadio.isSelected()) insertFieldsFrame.setTitle("Owner");
		else if(categoryRadio.isSelected()) insertFieldsFrame.setTitle("Category");
		
		fieldsFrameLayout=new BoxLayout(insertFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		insertFieldsFrame.setLayout(fieldsFrameLayout);
		fieldsPanel=new JPanel();
		fieldsPanelLayout=new GridLayout(2,2);
		fieldsPanel.setLayout(fieldsPanelLayout);
		fieldsPanel.add(idLabel);
		fieldsPanel.add(idField);
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		insertFieldsFrame.add(fieldsPanel);
		insertFieldsFrame.add(insertButton);
		insertFieldsFrame.pack();
		insertFieldsFrame.setVisible(true);
		insertFieldsFrame.setResizable(false);
		insertFieldsFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * default constructor
	 * constructs frame
	 */
	public Insert() {
		setRadio();
		setLabels();
		setFields();
		insertLabel=new JLabel("Insert");
		insertOptions=new JFrame("Insert Options");
		optionsLayout=new BoxLayout(insertOptions.getContentPane(),BoxLayout.Y_AXIS);
		insertOptions.setLayout(optionsLayout);
		submitButton=new JButton("Submit");
		insertButton=new JButton("Insert");
		insertOptions.add(insertLabel);
		insertOptions.add(radioPanel);
		insertOptions.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(artifactRadio.isSelected()) insertFrame();
				else if(materialRadio.isSelected() || ownerRadio.isSelected() || categoryRadio.isSelected()) genericFrame();
			}
		});
		insertOptions.pack();
		insertOptions.setVisible(true);
		insertOptions.setResizable(false);
		insertOptions.setLocationRelativeTo(null);
	}
}
