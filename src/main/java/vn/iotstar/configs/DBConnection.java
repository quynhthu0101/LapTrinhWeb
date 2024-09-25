package vn.iotstar.configs;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/world";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static void main(String[] args) {
		try {
			Connection c = getConnection();
			if(c==null) {
				System.out.println("Sthing wrong");
			} else {
				System.out.println("ok");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
