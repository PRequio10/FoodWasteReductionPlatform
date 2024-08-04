/**
 * 
 */
package fwrp.JUnitTest;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import org.junit.jupiter.api.Test;

import fwrp.connection.DBConnection;

/**
 * JDBC unit testing.
 * 
 * @author John Philip William Requio
 */
class DBConnectionTest {

	/**
	 * Test of getInstance and getConnection method.
	 * @throws SQLException
	 */
	@Test
	void getConnectionTest() throws SQLException {
		
		try {
			DBConnection instance = DBConnection.getInstance();
			Connection dbConn = instance.getConnection();
			
			assertNotNull(dbConn);
			System.out.println("Database Connection established");
			
		} catch (SQLException e) {
			System.out.println("Database Connection failed" + e.getMessage());
		}

	}
}
