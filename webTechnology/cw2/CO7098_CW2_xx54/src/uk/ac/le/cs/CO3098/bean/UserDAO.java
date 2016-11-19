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

public class UserDAO {

	private Connection dbConnection;
	private PreparedStatement pStmt;
	private final int CODEISNEW = 1;
	private final int CODEISNOTNEW = 2;
	private final int CODENOTEXIST = 3;

	private String SQL_SELECT = "SELECT userName FROM user WHERE userEmail = ? AND password = ?";
	private String SQL_INSERT = "INSERT INTO USER (userName,userEmail,userFullName,dateOfBirth,homeAddress,password,securityCode) "
			+ "VALUES (?,?,?,?,?,?,?)";

	public UserDAO() {
		dbConnection = DBConnection.getConnection();
	}

	public String verifyUser(String email, String password) {
		String userName = null;
		try {
			pStmt = dbConnection.prepareStatement(SQL_SELECT);
			pStmt.setString(1, email);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				userName = rs.getString("userName");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return userName;
	}
	
public int createUser(String userName, String userEmail, String userFullName,
			String dateOfBirth,String homeAddress,String password,String securityCode) {
		int result = 0;
		String passwordHash = HashGenerator.getSHA256(password);
		try {
			pStmt = dbConnection.prepareStatement(SQL_INSERT);
			pStmt.setString(1, userName);
			pStmt.setString(2, userEmail);
			pStmt.setString(3, userFullName);
			pStmt.setString(4, dateOfBirth);
			pStmt.setString(5, homeAddress);
			pStmt.setString(6, passwordHash);
			pStmt.setString(7, securityCode);
			result = pStmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return result;
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