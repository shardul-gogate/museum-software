package userInterface;

import java.awt.*;
import javax.swing.*;

public class Update {
	private JFrame updateOptions;
	
	private JPanel radioPanel;
	
	private FlowLayout frameLayout;
	
	private GridLayout radioPanelLayout;
	
	private JRadioButton artifactRadio,materialRadio,categoryRadio,collectRadio,ownerRadio;
	
	private ButtonGroup radioGroup;
	
	private JButton submitButton;
	
	private JLabel updateLabel,idLabel;
	
	private JTextField idField;
	
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

		radioPanelLayout=new GridLayout(5,1,0,5);
		radioPanel=new JPanel();
		radioPanel.setLayout(radioPanelLayout);
		radioPanel.add(artifactRadio);
		radioPanel.add(categoryRadio);
		radioPanel.add(materialRadio);
		radioPanel.add(collectRadio);
		radioPanel.add(ownerRadio);
	}
	
	public Update() {
		setRadio();
		
		updateLabel=new JLabel("Update entry in table:");
		
		idLabel= new JLabel("Enter ID:");
		idField=new JTextField(10);
		
		submitButton=new JButton("Submit");	
		
		frameLayout=new FlowLayout();
		
		updateOptions=new JFrame();
		updateOptions.setLayout(frameLayout);
		updateOptions.setVisible(true);
		updateOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		updateOptions.add(updateLabel);
		updateOptions.add(radioPanel);
		updateOptions.add(idLabel);
		updateOptions.add(idField);
		updateOptions.add(submitButton);
		updateOptions.pack();
	}
}
