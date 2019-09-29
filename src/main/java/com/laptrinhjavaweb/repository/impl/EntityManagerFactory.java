package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EntityManagerFactory {
	
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	
	/*connection de tao ra prepareStatement de query dl vao resultSet*/
	public static Connection getConnection() { 
		try {
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(url,user,password);
		}catch(ClassNotFoundException | SQLException e){
			return null;
		}
	}
}
