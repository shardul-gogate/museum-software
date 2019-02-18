package windowManager;

import java.awt.event.*;
import java.sql.SQLException;

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
	
	private int performDelete(int deleteId) {
		int confirmDelete=0;
		try {
			if(artifactRadio.isSelected()) {
				dbMain.deleteArtifact.setInt(1,deleteId);
				confirmDelete=dbMain.deleteArtifact.executeUpdate();
			}
			else if(materialRadio.isSelected() ){
				dbMain.deleteMaterial.setInt(1,deleteId);
				confirmDelete=dbMain.deleteMaterial.executeUpdate();
			}
			else if(ownerRadio.isSelected()) {
				dbMain.deleteOwner.setInt(1,deleteId);
				confirmDelete=dbMain.deleteOwner.executeUpdate();
			}
			else if(categoryRadio.isSelected()) {
				dbMain.deleteCategory.setInt(1,deleteId);
				confirmDelete=dbMain.deleteCategory.executeUpdate();
			}
			else if(collectRadio.isSelected()) {
				dbMain.deleteCollection.setInt(1,deleteId);
				confirmDelete=dbMain.deleteCollection.executeUpdate();
			}
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
		return confirmDelete;
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
				String queryId=idField.getText();
				if(queryId.isEmpty()) {
					JOptionPane.showMessageDialog(deleteOptions,"ID input field is empty","No input",JOptionPane.ERROR_MESSAGE);
					return;
				}
				int deleteId=Integer.parseInt(queryId);
				int confirmDelete=0;
				int clickedResult=JOptionPane.showConfirmDialog(deleteOptions, "Are you sure you want to delete the entry?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
				if(clickedResult==JOptionPane.YES_OPTION) {
					confirmDelete=performDelete(deleteId);
					if(confirmDelete>0) {
						JOptionPane.showMessageDialog(null,"Your record has been succesfully\ndeleted from the databse","Success",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null,"There was an error deleting the record\nAre you sure you entred the ID right?","Unable to delete",JOptionPane.ERROR_MESSAGE);
						return;
					}
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
