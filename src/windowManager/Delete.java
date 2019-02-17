package windowManager;

import java.awt.event.*;
import javax.swing.*;

public class Delete {

	private Database dbMain;
	private JFrame deleteOptions;
	private JPanel radioPanel;
	private BoxLayout frameLayout,radioPanelLayout;
	private JRadioButton artifactRadio,materialRadio,categoryRadio,collectRadio,ownerRadio;
	private ButtonGroup radioGroup;
	private JButton submitButton;
	private JLabel deleteLabel,idLabel;
	private JTextField idField;
	
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
	public Delete(Database dbMain) {
		this.dbMain=dbMain;
		setRadio();
		deleteLabel=new JLabel("Update entry in table:");
		idLabel= new JLabel("Enter ID:");
		idField=new JTextField(10);
		submitButton=new JButton("Submit");	
		deleteOptions=new JFrame("Delete Options");
		frameLayout=new BoxLayout(deleteOptions.getContentPane(),BoxLayout.Y_AXIS);
		deleteOptions.setLayout(frameLayout);
		deleteOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		deleteOptions.add(deleteLabel);
		deleteOptions.add(radioPanel);
		deleteOptions.add(idLabel);
		deleteOptions.add(idField);
		deleteOptions.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String queryString=idField.getText();
				if(queryString.isEmpty()) {
					JOptionPane.showMessageDialog(deleteOptions,"ID input field is empty","No input",JOptionPane.ERROR_MESSAGE);
					return;
				}
				int clickedResult=JOptionPane.showConfirmDialog(deleteOptions, "Are you sure you want to delete the entry?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
				if(clickedResult==JOptionPane.YES_OPTION) {
					return;
				}
				else if(clickedResult==JOptionPane.NO_OPTION) {
					return;
				}
			}
		});
		deleteOptions.setVisible(true);
		deleteOptions.pack();
		deleteOptions.setResizable(false);
		deleteOptions.setLocationRelativeTo(null);
	}
}
