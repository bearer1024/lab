package uk.ac.le.cs.CO3098.bean;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Map;
	import java.util.Map.Entry;
public class QuestionDao {
		private Connection dbConnection;
		private PreparedStatement pStmt;
		private final int CODEISNEW = 1;
		private final int CODEISNOTNEW = 2;
		private final int CODENOTEXIST = 3;

		private String SQL_SELECT = "SELECT * FROM QUESTION";
//		private String SQL_INSERT = "INSERT INTO USER (userName,userEmail,userFullName,dateOfBirth,homeAddress,password,securityCode) "
//				+ "VALUES (?,?,?,?,?,?,?)";

		public QuestionDao() {
			dbConnection = DBConnection.getConnection();
		}
		
		public String getQuestionFromDB(){
			String question = null;
			try {
				Statement statement = dbConnection.createStatement();
				ResultSet rs = statement.executeQuery(SQL_SELECT);
				while(rs.next()){
					String name = rs.getString("QUESTION");
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}

		public  User verifyUser(String email, String password) {
			User user = null;
			try {
				pStmt = dbConnection.prepareStatement(SQL_SELECT);
				pStmt.setString(1, email);
				String passwordHash = HashGenerator.getSHA256(password);
				pStmt.setString(2, passwordHash);
				try(ResultSet rs = pStmt.executeQuery();){
				while (rs.next()) {
					String userName = rs.getString("userName");
					String userFullName = rs.getString("userFullName");
					String dateOfBirth = rs.getString("dateOfBirth");
					String userEmail = rs.getString("userEmail");
					String homeAddress = rs.getString("homeAddress");
					String userPassword = rs.getString("password");
					String securityCode = rs.getString("securityCode");
					user = new User(userName,userEmail,userFullName,dateOfBirth,homeAddress,userPassword,securityCode);
				}
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return user;
		}
		

		
		public boolean emailIsRegistered(String email){
			try{
				String sql = "select userEmail from user";
				List<String> list = new ArrayList<String>();
				ResultSet resultSet = dbConnection.createStatement().executeQuery(sql);
				while(resultSet.next()){
					String userEmail= resultSet.getString(1);
					list.add(userEmail);
				}
				if(list.contains(email)){
					return true;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return false;
		}
		
		public int checkCodeStatus(String code){
			try {
				int used = 1;
				String sql = "select * from SECURITY_CODE";
				ResultSet resultSet = dbConnection.createStatement().executeQuery(sql);
				HashMap<String,Integer> hashmap = new HashMap<String, Integer>();
				while(resultSet.next()){
					String securityCode = resultSet.getString(1);
					int usedValue = resultSet.getInt(2);
					hashmap.put(securityCode, usedValue);
				}
				if(hashmap.containsKey(code)){
					if(hashmap.get(code)==0){
						return CODEISNEW;
					}else if(hashmap.get(code)==1){
						return CODEISNOTNEW;
					}
				}else{
					return CODENOTEXIST;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return CODENOTEXIST;
		}
		
	
		
}
