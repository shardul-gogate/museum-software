package windowManager;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * First screen of the software thus contains main method
 */
public class Login {
	protected Database dbMain;
	private JTextField userName;
	private JPasswordField passWord;
	private JFrame loginFrame;
	private JPanel loginPanel;
	private JLabel userLabel,passLabel;
	private JButton loginButton;
	private BoxLayout frameLayout;
	private GridLayout panelLayout;
	
	private boolean credentialsValid() {
		String userNameEntered=userName.getText();
		String passWordEntered=new String(passWord.getPassword());
		if(userNameEntered.isEmpty() || passWordEntered.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Please enter Username and Password properly","Empty Input",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			dbMain.rs=dbMain.stmt.executeQuery("SELECT password FROM admin WHERE username='" + userNameEntered + "';");
			if(dbMain.rs.next()) {
				String passWordInDb=dbMain.rs.getString(1);
				if(passWordEntered.equals(passWordInDb)) {
					return true;
				}
				JOptionPane.showMessageDialog(null,"Please enter a correct Password","Wrong Password",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		catch(SQLException sqle) {
			//dbMain.SQLExceptionMessage();
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
		JOptionPane.showMessageDialog(null,"Please enter a correct Username","Wrong Username",JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
	/**
	 * Default and only constructor of the class
	 * Constructs frame for accepting user input of username and password
	 * Opens home-page of the software if credentials are valid
	 */
	public Login() {
		dbMain=new Database();
		loginFrame=new JFrame("Login");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLayout=new BoxLayout(loginFrame.getContentPane(),BoxLayout.Y_AXIS);
		loginFrame.setLayout(frameLayout);
		userName=new JTextField(20);
		passWord=new JPasswordField(20);
		userLabel=new JLabel("Username");
		passLabel=new JLabel("Password");
		loginButton=new JButton("Login");
		loginPanel=new JPanel();
		panelLayout=new GridLayout(2,2);
		loginPanel.setLayout(panelLayout);
		loginPanel.add(userLabel);
		loginPanel.add(userName);
		loginPanel.add(passLabel);
		loginPanel.add(passWord);
		loginFrame.add(loginPanel);
		loginFrame.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(credentialsValid()) {
					loginFrame.dispose();
					new HomePage(dbMain);
				}
			}
		});
		loginFrame.pack();
		loginFrame.setResizable(false);
		loginFrame.setVisible(true);
		loginFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * Main method of the software
	 * Launches the software by giving call to the Login class constructor
	*/
	public static void main(String args[]) {
		Database dbMain=new Database();
		//new Login();
		new HomePage(dbMain);
		//new Search(dbMain);
		//new Insert(dbMain);
		//new Update(dbMain);
		//new Delete(dbMain);
		return;
	}
}
