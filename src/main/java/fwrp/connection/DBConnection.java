package fwrp.connection;

import java.sql.*;

/**
 * JDBC in singleton pattern.
 * 
 * @author John Philip William Requio
 */
public class DBConnection {
	
	private static DBConnection dbConnect;
	private static Connection conn;
	
	/**
	 * Class constructor - database information.
	 * @throws SQLException
	 */
	private DBConnection() throws SQLException{
		try {
			
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/FWRP";
			String user = "root";
			String pass = "Requio.10";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pass);
		}
		catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * DBConnection getInstance method - Singleton Double-Checked Locking
	 * @return DBConnection
	 * @throws SQLException
	 */
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
	
	/**
	 * getConnection method
	 * @return
	 */
	public Connection getConnection() {
		return conn;
	}
	
}
