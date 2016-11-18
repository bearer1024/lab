package uk.ac.le.cs.CO3098.bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class StudentDB {
	
	  private static Connection connect = null;
//	  private Statement statement = null;
//	  private ResultSet resultSet = null;
	  private static String host="mysql.mcscw3.le.ac.uk";
	  private static String database="yh37";
	  private static String username="yh37";
	  private static String password="****";

	public static void main(String args[]){
		
		StudentDB app=new StudentDB();
		//app.searchDatabase();
		app.updateDatabase();
		app.searchDatabase();
		app.preparedStatementSearch(2);
		
	}
	
	
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
	
	    public void updateDatabase(){
	    	try(
		      // setup the connection with the DB.
		      Connection connect = getConnection();
		      // statements allow to issue SQL queries to the database
		      Statement statement = connect.createStatement();
		    ){
		      statement.executeUpdate("UPDATE Student set ADDRESS='24 St Leonards Rd, Leicester, LE2 1WS' WHERE ID=2");
		      
		      
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    	}
	    }
		
	    
	    
	    public void preparedStatementSearch(int studentID){
	    	String sql="SELECT * from Student WHERE ID=?";
	    	try( Connection connect = getConnection(); 
		    	 PreparedStatement pstmt = connect.prepareStatement(sql);
	    		){	
		    	 pstmt.setInt(1,studentID);
		    	 try (ResultSet rs = pstmt.executeQuery();){
		    	   while(rs.next()){
			    	  String name=rs.getString("Name");			    	  
			    	  String address=rs.getString("Address");  	  
			    	  System.out.println("name:"+name+" address:"+address); 
			      }
		    	 }
	    	}catch(SQLException ex){
	    		ex.printStackTrace();	
	    	}	 
	    }
	    
	    
		public void searchDatabase(){
	
			try {
			      // this will load the MySQL driver, each DB has its own driver
			      // setup the connection with the DB.
			      Connection connect = getConnection();
			      // statements allow to issue SQL queries to the database
			      Statement statement = connect.createStatement();
			      // resultSet gets the result of the SQL query
			      ResultSet resultSet = statement.executeQuery("SELECT * from Student");      
			      // resultSet is initialised before the first data set
			      while(resultSet.next()){
			    	  
			    	   // it also is possible to get the columns via name
			          // also possible to get the columns via the column number
			          // which starts at 1
			          // e.g., resultSet.getString(2) is the same as resultSet.getString("username"); 
			    	  
			    	  String name=resultSet.getString("Name");			    	  
			    	  String address=resultSet.getString("Address");
			      	  System.out.println("name:"+name+" address:"+address);  
			      }
			      
			   
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		
		
//		public boolean checkUser(String user, String pass){
//			
//			try {
//
//			      Class.forName("com.mysql.jdbc.Driver");
//			      connect = DriverManager.getConnection("jdbc:mysql://localhost/AlstomDB","root","server2007!");
//			      statement = connect.createStatement();
//			      resultSet = statement.executeQuery("SELECT * from AlstomDB.User");
//
//			      while(resultSet.next()){
//	
//			    	  String username=resultSet.getString("username");			    	  
//			    	  String password=resultSet.getString("password");
//			    	  
//			    	  if(user!=null && password!=null){
//			    		  if(user.equals(username) && pass.equals(password)){
//			    			  return true;
//			    		  }
//			    	  }
//			    	  
//			      }
//			      
//			   
//			}catch(Exception ex){
//				return false;
//			}
//			
//			return false;
//			
//			
//		}
	

}
