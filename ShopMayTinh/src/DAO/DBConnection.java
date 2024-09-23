package DAO;
import java.sql.*;
import GUI.MessageBox;

abstract class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DB = "qlchlaptop";
	private static final String USER = "root";
	private static final String PASS = "";
	
	static Connection connect() {
		try {
			return DriverManager.getConnection(URL+DB, USER, PASS);
		}
		catch (SQLException e) {
			MessageBox.loi("Chưa kết nối với CSDL");
			System.exit(1);
			return null;
		}
	}
}