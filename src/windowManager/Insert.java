package windowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Insert {
	private JRadioButton artifactRadio,materialRadio,categoryRadio,collectRadio,ownerRadio;
	private ButtonGroup radioGroup;
	private JButton submitButton;
	private JPanel radioPanel;
	private JFrame insertOptions,insertFieldsFrame;
	private BoxLayout optionsLayout,fieldsLayout,radioLayout;
	private JLabel insertLabel;
	private JTextField nameField,yearField,locField,remarkField;
	private JTextArea desciption;
	private JComboBox collectBox,categoryBox,materialBox,OwnerBox;
	
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
	
	private void insertFields() {
		insertFieldsFrame=new JFrame("Input Fields");
		fieldsLayout=new BoxLayout(insertFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		insertFieldsFrame.setLayout(fieldsLayout);
		
		insertFieldsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		insertFieldsFrame.pack();
		insertFieldsFrame.setVisible(true);
		insertFieldsFrame.setLocationRelativeTo(null);
		insertFieldsFrame.setResizable(false);
	}
	
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
				insertFields();
			}
		});
		insertOptions.pack();
		insertOptions.setVisible(true);
		insertOptions.setResizable(false);
		insertOptions.setLocationRelativeTo(null);
	}
}
