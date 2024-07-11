package fwrp.connection;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DBConnection {
	
	private static DBConnection dbConnect;
	private static Connection conn;
	Properties props = new Properties();
	
	private DBConnection() throws SQLException{
		try {
			InputStream input = new FileInputStream("src/main/data/database.properties");
			props.load(input);
			
			String driver = props.getProperty("driver");
			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String pass = props.getProperty("pass");
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pass);
		}
		catch (IOException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static synchronized DBConnection getInstance() throws SQLException {
		DBConnection dbConn = DBConnection.dbConnect;
		if (dbConn == null) {
			synchronized (DBConnection.class) {
				dbConn = DBConnection.dbConnect;
				if (dbConn == null) {
					DBConnection.dbConnect = dbConn = new DBConnection();
				}
			}
		}
		return dbConn;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
}
