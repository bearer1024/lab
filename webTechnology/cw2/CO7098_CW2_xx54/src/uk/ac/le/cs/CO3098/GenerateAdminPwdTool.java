package uk.ac.le.cs.CO3098;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.le.cs.CO3098.bean.DBConnection;
import uk.ac.le.cs.CO3098.bean.HashGenerator;
import uk.ac.le.cs.CO3098.bean.UserDAO;

public class GenerateAdminPwdTool {

	public static void main(String[] args) {
		String sql = "insert into admin (name,password) values(?,?)";
		System.out.println("set up admin info");

		try {
		PreparedStatement pStmt = DBConnection.getConnection().prepareStatement(sql);
		pStmt.setString(1, "admin");
		String hash = HashGenerator.getSHA256("admin");
		pStmt.setString(2, hash);
		int result = pStmt.executeUpdate();
		if(result==1){
			System.out.println("rember admin info is: admin,admin");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
