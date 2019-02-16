package windowManager;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

/**
 * Class used for the page of Updating the artifact
 */
public class Update {
	private JFrame updateOptions,updateFieldsFrame;
	private JRadioButton artifactRadio,materialRadio,categoryRadio,collectRadio,ownerRadio;
	private ButtonGroup radioGroup;
	private JButton submitButton,insertButton;
	private JPanel radioPanel,fieldsPanel;
	private BoxLayout optionsLayout,fieldsoptionsLayout,radioLayout;
	private GridLayout fieldsPanelLayout;
	private JTextField idField,nameField,yearField,locField,remarkField;
	private JTextArea description;
	private JComboBox collectBox,categoryBox,materialBox,OwnerBox;
	private JLabel idLabel,nameLabel,yearLabel,locLabel,remarkLabel,descLabel,collectLabel,ownerLabel,materialLabel,categoryLabel,insertLabel,updateLabel;
	
	/**
	 * constructs radio button and panel
	 */
	private void setRadio() {
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
		radioPanel=new JPanel();
		radioLayout=new BoxLayout(radioPanel,BoxLayout.Y_AXIS);
		radioPanel.setLayout(radioLayout);
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
		idField=new JTextField(15);
		nameField=new JTextField(15);
		remarkField=new JTextField(15);
		locField=new JTextField(15);
		yearField=new JTextField(15);
		description=new JTextArea(5,15);
	}
	
	private void artifactFrame() {
		updateFieldsFrame=new JFrame("Insert");
		
		updateFieldsFrame.pack();
		updateFieldsFrame.setVisible(true);
		updateFieldsFrame.setResizable(false);
		updateFieldsFrame.setLocationRelativeTo(null);
	}
	
	private void genericFrame() {
		updateFieldsFrame=new JFrame();
		if(materialRadio.isSelected()) updateFieldsFrame.setTitle("Material");
		else if(ownerRadio.isSelected()) updateFieldsFrame.setTitle("Owner");
		else if(categoryRadio.isSelected()) updateFieldsFrame.setTitle("Category");
		fieldsoptionsLayout=new BoxLayout(updateFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		updateFieldsFrame.setLayout(fieldsoptionsLayout);
		fieldsPanel=new JPanel();
		fieldsPanelLayout=new GridLayout(2,2);
		fieldsPanel.setLayout(fieldsPanelLayout);
		idLabel.setText("ID");
		fieldsPanel.add(idLabel);
		fieldsPanel.add(idField);
		nameLabel.setText("Name");
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		updateFieldsFrame.add(fieldsPanel);
		updateFieldsFrame.add(insertButton);
		updateFieldsFrame.pack();
		updateFieldsFrame.setVisible(true);
		updateFieldsFrame.setResizable(false);
		updateFieldsFrame.setLocationRelativeTo(null);
	}
	
	private void collectFrame() {
		updateFieldsFrame=new JFrame("Collection");
		fieldsoptionsLayout=new BoxLayout(updateFieldsFrame.getContentPane(),BoxLayout.Y_AXIS);
		updateFieldsFrame.setLayout(fieldsoptionsLayout);
		fieldsPanel=new JPanel();
		fieldsPanelLayout=new GridLayout(3,2);
		fieldsPanel.setLayout(fieldsPanelLayout);
		idLabel.setText("ID");
		fieldsPanel.add(idLabel);
		fieldsPanel.add(idField);
		nameLabel.setText("Given By"); //Here, name refers to the name of the donator
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		yearLabel.setText("Given in Year"); //here, year refers to the year collection was donated
		fieldsPanel.add(yearLabel);
		fieldsPanel.add(yearField);
		updateFieldsFrame.add(fieldsPanel);
		updateFieldsFrame.add(insertButton);
		updateFieldsFrame.pack();
		updateFieldsFrame.setVisible(true);
		updateFieldsFrame.setResizable(false);
		updateFieldsFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * default constructor
	 * constructs frame and adds components
	 */
	public Update() {
		setRadio();
		setLabels();
		setFields();
		updateLabel=new JLabel("Update entry in table:");
		submitButton=new JButton("Submit");	
		insertButton=new JButton("Insert");
		updateOptions=new JFrame("Update Options");
		optionsLayout=new BoxLayout(updateOptions.getContentPane(),BoxLayout.Y_AXIS);
		updateOptions.setLayout(optionsLayout);
		updateOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		updateOptions.add(updateLabel);
		updateOptions.add(radioPanel);
		idLabel.setText("Enter ID:");
		updateOptions.add(idLabel);
		updateOptions.add(idField);
		updateOptions.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String queryString=idField.getText();
				if(queryString.isEmpty()) {
					JOptionPane.showMessageDialog(updateOptions,"ID input field is empty","No input",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(artifactRadio.isSelected()) artifactFrame();
				else if(materialRadio.isSelected() || ownerRadio.isSelected() || categoryRadio.isSelected()) genericFrame();
				else if(collectRadio.isSelected()) collectFrame();
			}
		});
		updateOptions.setVisible(true);
		updateOptions.pack();
		updateOptions.setResizable(false);
		updateOptions.setLocationRelativeTo(null);
	}
}
