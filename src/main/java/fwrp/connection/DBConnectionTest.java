/**
 * 
 */
package fwrp.connection;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import org.junit.jupiter.api.Test;

/**
 * 
 */
class DBConnectionTest {

	@Test
	void getConnectionTest() throws SQLException {
		DBConnection instance = DBConnection.getInstance();
		Connection dbConn = instance.getConnection();
		
		if (dbConn != null) {
			System.out.println("Database connection created");
		} else {
			System.out.println("Unable to create database connection");
		}
	}

}
