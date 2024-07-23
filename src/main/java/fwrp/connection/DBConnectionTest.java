/**
 * 
 */
package fwrp.connection;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import org.junit.jupiter.api.Test;

/**
 * JDBC unit testing.
 * 
 * @author John Philip William Requio
 */
class DBConnectionTest {

	@Test
	void getConnectionTest() throws SQLException {
		DBConnection instance = DBConnection.getInstance();
		Connection dbConn = instance.getConnection();
		
		assertNotNull(dbConn);
	}
}
