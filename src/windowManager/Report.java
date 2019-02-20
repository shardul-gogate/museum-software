package windowManager;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Report {
	
	private Database dbMain;
	private JFrame repFrame;
	private JTextArea repArea;
	
	private void artifactRep() {
		int countArt,maxLoc=0,maxMat=0,maxCat=0;
		String location=null,material=null,category=null;
		try {
			dbMain.rs=dbMain.stmt.executeQuery("SELECT count(artifactid) FROM artifact");
			if(dbMain.rs.next()) {
				countArt=dbMain.rs.getInt(1);
				repArea.append("\n     Number of Artifacts in the Museum: " + countArt + "\n");
			}
			dbMain.rs=dbMain.stmt.executeQuery("SELECT location,count(artifactid) FROM artifact group by location");
			while(dbMain.rs.next()) {
				int currCount=dbMain.rs.getInt(2);
				if(currCount>maxLoc) {
					maxLoc=currCount;
					location=dbMain.rs.getString(1);
				}
			}
			repArea.append("\n\n     Location with most number of artifacts: " + location + " has " + maxLoc + " artifacts\n");
			dbMain.rs=dbMain.stmt.executeQuery("SELECT categoryname,count(artifactid) FROM category,artifact where category.categoryid=artifact.categoryid group by categoryname");
			while(dbMain.rs.next()) {
				int currCount=dbMain.rs.getInt(2);
				if(currCount>maxCat) {
					maxCat=currCount;
					category=dbMain.rs.getString(1);
				}
			}
			repArea.append("\n\n     Category with most number of artifacts: " + category + " has " + maxCat + " artifacts\n");
			dbMain.rs=dbMain.stmt.executeQuery("SELECT materialname,count(artifactid) FROM material,artifact where material.materialid=artifact.materialid group by materialname");
			while(dbMain.rs.next()) {
				int currCount=dbMain.rs.getInt(2);
				if(currCount>maxMat) {
					maxMat=currCount;
					material=dbMain.rs.getString(1);
				}
			}
			repArea.append("\n\n     Material with most number of artifacts: " + material + " has " + maxMat + " artifacts\n");
			dbMain.rs=dbMain.stmt.executeQuery("SELECT min(year) FROM artifact");
			int minYear=0;
			if(dbMain.rs.next()) {
				minYear=dbMain.rs.getInt(1);
			}
			repArea.append("\n\n     Oldest artifact in the museum is from year: " + minYear + "\n");
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void materialRep() {
		try {
			dbMain.rs=dbMain.stmt.executeQuery("SELECT materialname,count(artifactid) FROM artifact,material WHERE artifact.materialid=material.materialid group by materialname");
			repArea.append("\n\n\tMaterial - No. of artifacts\n");
			while(dbMain.rs.next()) {
				String material=dbMain.rs.getString(1);
				int countArt=dbMain.rs.getInt(2);
				repArea.append("\n\t" + material + " - " + countArt + "\n");
			}
		}
		catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null,sqle,"SQL Exception Occured",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Report(Database dbMain,int repFlag) {
		this.dbMain=dbMain;
		repFrame=new JFrame("Report");
		repArea=new JTextArea(20,40);
		repArea.setEditable(false);
		repFrame.add(repArea);
		repFrame.pack();
		repFrame.setVisible(true);
		repFrame.setLocationRelativeTo(null);
		repFrame.setResizable(false);
		repArea.append("\n\t\tREPORT\n\n");
		switch(repFlag) {
		case 1:
			artifactRep();
			break;
		case 2:
			materialRep();
			break;
		}
	}
}
