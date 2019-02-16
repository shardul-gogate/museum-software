package windowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * First screen of the software thus contains main method
 */
public class Login {
	private Font f = new Font("Comic Sans MS", Font.BOLD, 22);
	private JTextField userName;
	private JPasswordField passWord;
	private JFrame loginFrame;
	private JPanel loginPanel;
	private JLabel userLabel,passLabel;
	private JButton loginButton;
	private BoxLayout frameLayout;
	private GridLayout panelLayout;
	private Connection con;
	//private ResultSet rs1,rs2;
	//private Statement stmt;
	
	/**
	 * Default and only constructor of the class
	 * Constructs frame for accepting user input of username and password
	 * Opens home-page of the software if credentials are valid
	 */
	public Login() {
		loginFrame=new JFrame("Login");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLayout=new BoxLayout(loginFrame.getContentPane(),BoxLayout.Y_AXIS);
		loginFrame.setLayout(frameLayout);
		userName=new JTextField(20);
		passWord=new JPasswordField(20);
		userLabel=new JLabel("Username");
		userLabel.setFont(f);
		userLabel.setForeground(Color.black);
		passLabel=new JLabel("Password");
		passLabel.setFont(f);
		passLabel.setForeground(Color.black);
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
		loginPanel.setBorder(BorderFactory.createEtchedBorder(Color.red,Color.blue));
		try{
			con=null;
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","onkar@123");
			
			if(con==null) {
				JOptionPane.showMessageDialog(null,"Error in connection","No connection",JOptionPane.ERROR_MESSAGE);
			}
			//System.out.println("Connection Successful!!");
			}catch(Exception e) {}
		
		
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				int i=validate();
				if(i==1) {
				new HomePage();
				loginFrame.dispose();
				}
				/*else {
					JOptionPane.showMessageDialog(null,"Error logging in","Error",JOptionPane.ERROR_MESSAGE);
				}*/
			}
		});
		loginFrame.pack();
		loginFrame.setResizable(false);
		loginFrame.setVisible(true);
		loginFrame.setLocationRelativeTo(null);
	}
	
	int validate() {
		try {
			ResultSet rs1=null;
			//ResultSet rs2=null;
			Statement stmt=con.createStatement();
			
			rs1=stmt.executeQuery("Select * from admin");
			//rs2=stmt.executeQuery("Select * from admin");
			//ResultSetMetaData rsmd=rs.getMetaData();
			char p[]=passWord.getPassword();
			String pwd=new String(p);
			String usr=userName.getText();
			//System.out.println("Username: "+usr);
			//System.out.println("Password: "+pwd);
			//rs1=stmt.executeQuery("Select * from admin where ad_usr='"+usr+"'");
			rs1=stmt.executeQuery("Select ad_pass from admin where ad_usr='"+usr+"'");
			while(rs1.next()) {
				if(pwd.equals(rs1.getString(1))) {
				return 1;
				}
				JOptionPane.showMessageDialog(null, "Password is Wrong", "Error", JOptionPane.ERROR_MESSAGE);	
				return 0;
				//JOptionPane.showMessageDialog(null,"Hello","Hello",JOptionPane.DEFAULT_OPTION);
				//return flag;
			}
			/*while() {
				flag2=1;
			}*/
			
			//JOptionPane.showMessageDialog(null, "Error in connection", "Please check your username and password", JOptionPane.ERROR_MESSAGE);	
			//flag=0;
			}catch(Exception e) {}
		JOptionPane.showMessageDialog(null, "Username doesnt exist", "Error", JOptionPane.ERROR_MESSAGE);	
		return 0;
	}
	
	/**
	 * Main method of the software
	 * Launches the software by giving call to the Login class constructor
	 */
	public static void main(String args[]) {
		new Login();
		//new HomePage();
		//new Search();
		//new Insert();
		//new Update();
		//new Delete();
		return;
	}
}
