package uk.ac.le.cs.CO3098.bean;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
private static Connection connection = null;

public static Connection getConnection() {
	if (connection != null)
		return connection;
	else {
		try 
		{  
//		final String host = "mysql.mcscw3.le.ac.uk";
		final String host = "localhost";
		final String database = "xx54";
//		final String username = "xx54";
		final String username = "root";
//		final String password = "elereivu";
		final String password = "root";
		Class.forName("com.mysql.jdbc.Driver");
		String conn_string="jdbc:mysql://"+host+"/"+database;
		connection = DriverManager.getConnection(conn_string,username,password);
		return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	}
}