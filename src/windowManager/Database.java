package windowManager;

import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Database {
	public Connection conn;
	public Statement stmt;
	public ResultSet rs;
	public ResultSetMetaData rsmd;
	public PreparedStatement insertArtifact,insertCategory,insertOwner,insertMaterial,insertCollection;
	public PreparedStatement updateArtifact,updateCategory,updateOwner,updateMaterial,updateCollection;
	public PreparedStatement deleteArtifact,deleteCategory,deleteMaterial,deleteOwner,deleteCollection;
	public PreparedStatement searchArtifact;
	
	public String getEntryName(String colomnName,String tableName,String key,int value) {
		String colomnData=null;
		try {
			rs=stmt.executeQuery("SELECT " + colomnName + " FROM " + tableName + " WHERE " + key + "=" + value);
			while(rs.next()) {
				colomnData=rs.getString(1);
			}
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
		return colomnData;
	}
	
	public int getEntryId(String colomnName,String tableName,String key,String value) {
		int colomnData=0;
		try {
			rs=stmt.executeQuery("SELECT " + colomnName + " FROM " + tableName + " WHERE " + key + "='" + value +"'");
			while(rs.next()) {
				colomnData=Integer.parseInt(rs.getString(1));
			}
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
		return colomnData;
	}
	
	public Vector<String> getAllEntries(String colomnName,String tableName) {
		Vector<String> listContents=new Vector<String>();
		try {
			rs=stmt.executeQuery("SELECT " + colomnName + " FROM " + tableName);
			while(rs.next()) {
				listContents.add(rs.getString(1));
			}
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
		return listContents;
	}
	
	private void prepareAllStatements() {
		try {
			insertArtifact=conn.prepareStatement("INSERT INTO artifact VALUES(?,?,?,?,?,?,?,?,?,?);");
			insertOwner=conn.prepareStatement("INSERT INTO owner VALUES(?,?);");
			insertCategory=conn.prepareStatement("INSERT INTO category VALUES(?,?);");
			insertMaterial=conn.prepareStatement("INSERT INTO material VALUES(?,?);");
			insertCollection=conn.prepareStatement("INSERT INTO collection VALUES(?,?,?);");
			updateArtifact=conn.prepareStatement("UPDATE artifact SET artifactId=?,artifactName=?,location=?,remark=?,description=?,year=?,materialId=?,categoryId=?,ownerId=?,collectId=? WHERE artifactId=?");
			updateMaterial=conn.prepareStatement("UPDATE material SET materialId=?,materialName=? WHERE materialId=?");
			updateOwner=conn.prepareStatement("UPDATE owner SET ownerId=?,ownerName=? WHERE ownerId=?");
			updateCategory=conn.prepareStatement("UPDATE category SET categoryId=?,categoryName=? WHERE categoryId=?;");
			updateCollection=conn.prepareStatement("UPDATE collection SET collectId=?,givenby=?,giveninyear=? WHERE collectId=?");
			deleteArtifact=conn.prepareStatement("DELETE FROM artifact WHERE artifactid=?;");
			deleteCategory=conn.prepareStatement("DELETE FROM category WHERE categoryid=?;");
			deleteMaterial=conn.prepareStatement("DELETE FROM material WHERE materialid=?;");
			deleteOwner=conn.prepareStatement("DELETE FROM owner WHERE ownerid=?;");
			deleteCollection=conn.prepareStatement("DELETE FROM collection WHERE collectid=?;");
			searchArtifact=conn.prepareStatement("SELECT * FROM artifact WHERE ?=?;");
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Database() {
		try {
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","abcd1234");
			stmt=conn.createStatement();
			prepareAllStatements();
		}
		catch(ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null,cnfe,"ClassNotFound Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
	}
}
