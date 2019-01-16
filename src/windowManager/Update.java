package windowManager;

import javax.swing.*;
import java.awt.event.*;

/**
 * Class used for the page of Updating the artifact
 */
public class Update {
	private JFrame updateOptions;
	private JPanel radioPanel;
	private BoxLayout frameLayout,radioPanelLayout;
	private JRadioButton artifactRadio,materialRadio,categoryRadio,collectRadio,ownerRadio;
	private ButtonGroup radioGroup;
	private JButton submitButton;
	private JLabel updateLabel,idLabel;
	private JTextField idField;
	
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
		radioPanelLayout=new BoxLayout(radioPanel,BoxLayout.Y_AXIS);
		radioPanel.setLayout(radioPanelLayout);
		radioPanel.add(artifactRadio);
		radioPanel.add(categoryRadio);
		radioPanel.add(materialRadio);
		radioPanel.add(collectRadio);
		radioPanel.add(ownerRadio);
	}
	
	/**
	 * default constructor
	 * constructs frame and adds components
	 */
	public Update() {
		setRadio();
		updateLabel=new JLabel("Update entry in table:");
		idLabel= new JLabel("Enter ID:");
		idField=new JTextField(10);
		submitButton=new JButton("Submit");	
		updateOptions=new JFrame("Update Options");
		frameLayout=new BoxLayout(updateOptions.getContentPane(),BoxLayout.Y_AXIS);
		updateOptions.setLayout(frameLayout);
		updateOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		updateOptions.add(updateLabel);
		updateOptions.add(radioPanel);
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
				String radioSelected=getSelectedRadio();
				if(radioSelected.isEmpty()) {
					JOptionPane.showMessageDialog(updateOptions,"None of the categories are selected","No selection",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		updateOptions.setVisible(true);
		updateOptions.pack();
		updateOptions.setResizable(false);
		updateOptions.setLocationRelativeTo(null);
	}
}
