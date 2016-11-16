package uk.ac.le.cs.CO3098;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*; 
import javax.servlet.http.*;

public class GetStudentInfo extends HttpServlet {
	
	//  private static Connection connect = null;
	  private static String host="mysql.mcscw3.le.ac.uk";
	  private static String database="yh37";
	  private static String username="yh37";
	  private static String password="helloworld";
	
	public void doGet(HttpServletRequest req, 
			        HttpServletResponse res) 
	            throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String id=req.getParameter("id");
		
		try{
			if(id!=null && id.length()!=0){
		    	String sql="SELECT * from Student WHERE ID=?";
	   		 Class.forName("com.mysql.jdbc.Driver");
	   			String conn_string="jdbc:mysql://"+host+"/"+database;
			   	try( Connection connect = DriverManager.getConnection(conn_string,username,password);
				    	 PreparedStatement pstmt = connect.prepareStatement(sql);
			    		){	
				    	 pstmt.setInt(1,Integer.parseInt(id));
				    	 try (ResultSet rs = pstmt.executeQuery();){
				    	   while(rs.next()){
				    		   out.println(rs.getString("Name"));			    	  
					      }
				    	 }
			    	}catch(SQLException ex){
			    	    out.println("Error");
			    		ex.printStackTrace();	
			    	}	 
 	
			}else{
				out.println("Student id not provided");
			}
		}catch(Exception ex){
				out.println("MySQL driver not found");		
		}
		out.close();
	}
	public void doPost(HttpServletRequest req, 
				HttpServletResponse res) 
	            throws ServletException, IOException {
		doGet(req, res);
	}
}

