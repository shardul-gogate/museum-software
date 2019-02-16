package windowManager;

import java.sql.*;

import javax.swing.JOptionPane;

public class Database {
	public Connection conn;
	public Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsmd;
	
	public void SQLExceptionMessage() {
		JOptionPane.showMessageDialog(null,"Sorry!\nSQL encountered an error","SQL Exception",JOptionPane.ERROR_MESSAGE);
	}
	
	public void UnknownErrorMessage() {
		JOptionPane.showMessageDialog(null,"Sorry!\nAn unknown error was encountered","Unknown Error",JOptionPane.ERROR_MESSAGE);
	}
	
	public String getMaterialName(int materialId) {
		String materialName=null;
		try {
			rs=stmt.executeQuery("SELECT mat_nam FROM mateial WHERE mat_id=" + materialId);
			materialName= rs.getString(1);
		}
		catch(SQLException sqle) {
			SQLExceptionMessage();
		}
		return materialName;
	}
	
	public String getCategoryName(int categorylId) {
		String categoryName=null;
		try {
			rs=stmt.executeQuery("SELECT cat_name FROM mateial WHERE cat_id=" + categorylId);
			categoryName= rs.getString(1);
		}
		catch(SQLException sqle) {
			SQLExceptionMessage();
		}
		return categoryName;
	}
	
	public String getCollectionName(int collectionlId) {
		String collectionName=null;
		try {
			rs=stmt.executeQuery("SELECT collect_name FROM mateial WHERE collect_id=" + collectionlId);
			collectionName= rs.getString(1);
		}
		catch(SQLException sqle) {
			SQLExceptionMessage();
		}
		return collectionName;
	}
	
	public String getOwnerName(int ownerId) {
		String ownerName=null;
		try {
			rs=stmt.executeQuery("SELECT owner_name FROM mateial WHERE owner_id=" + ownerId);
			ownerName= rs.getString(1);
		}
		catch(SQLException sqle) {
			SQLExceptionMessage();
		}
		return ownerName;
	}
	
	public Database() {
		try {
			Class.forName("org.postgresql.driver");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","abcd1234");
		}
		catch(ClassNotFoundException cnfe) {
			UnknownErrorMessage();
		}
		catch(SQLException sqle) {
			SQLExceptionMessage();
		}
	}
}
