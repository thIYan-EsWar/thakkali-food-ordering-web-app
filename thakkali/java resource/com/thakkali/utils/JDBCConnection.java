package com.thakkali.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	private static Connection connection;
	private static String url = "jdbc:mysql://localhost:3306/thakkali";
	private static String user= "root";
	private static String password = "root";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection(
					url, 
					user, 
					password);
		} catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
	}
	
	public static Connection getDatabaseConnection() {
		return connection;
	}

}
