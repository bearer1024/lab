package uk.ac.le.cs.CO3098.bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.*;
import java.math.*;

public class UserVerification {
	
	  private static Connection connect = null;
	  private static String host="localhost";
	  private static String database="xx54";
	  private static String username="root";
	  private static String password="root";
	  
	  public static Connection getConnection(){
			if(connect ==null){
				try{
				 Class.forName("com.mysql.jdbc.Driver");
			      String conn_string="jdbc:mysql://"+host+"/"+database;
			      Connection connect = DriverManager.getConnection(conn_string,username,password);
			      return connect;
				}catch(Exception ex){
					 return null;
					//ex.printStackTrace();
				}
			}else{
				return connect;
			}
		}
	  
	  public User checkUser(String user,String password){
		  
		  String sql="SELECT * from User WHERE UserName=? AND PasswordHash=?";
		  User u=null;
	    	try( Connection connect = getConnection(); 
		    	 PreparedStatement pstmt = connect.prepareStatement(sql);
	    		){	
		    	 pstmt.setString(1,user);
		    	 pstmt.setString(2,MD5HashGenerator.getMD5Hash(password));
		    	 try (ResultSet rs = pstmt.executeQuery();){
		    	   while(rs.next()){    		 
			    	  String uname=rs.getString("UserName");		
			    	  String fname=rs.getString("FullName");	
			    	  String pass=rs.getString("PasswordHash");  	
			    	  u=new User(uname,fname,pass);
			    	  break;
			    	
			      }
		    	 }
	    	}catch(SQLException ex){
	    		ex.printStackTrace();	
	    	}
	    	return u;
		  
	  }
	  
	  public static void main(String[] args){
		 UserVerification DBoperator=new UserVerification();
		 System.out.println(DBoperator.checkUser("admin", "helloworld11"));
		  
	  }

}
