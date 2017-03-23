package com.child.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
	static Connection conn = null;

	static String url = "jdbc:mysql://localhost:3306/qrcode";

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

		String username = "root"; 
		String password = "root"; 
			

			conn = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
