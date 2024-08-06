package fwrp.JUnitTest;


import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import org.junit.jupiter.api.Test;
import fwrp.dao.UserDAOImpl;
import fwrp.model.User;


/**
 * userDAO unit testing.
 * 
 * @author John Philip William Requio
 */
class UserDAOTest {
	
	User user = new User();
	UserDAOImpl userData = new UserDAOImpl();
	

	/**
	 * Unit test of the insertUser method.
	 * @throws SQLException
	 */
	@Test
	void userInserttest() throws SQLException {
		
		user.setUserName("testUName");
		user.setPassWord("testPW");
		user.setEmail("test@test.com");
		user.setPhone(658974123);
		user.setUserType("Consumer");
		
		try {
			userData.insertUser(user);
			assertNotNull(userData);
			System.out.println("Successfully inserted user data.");
		} catch (SQLException e) {
			System.out.println("Failed to insert user date" + e.getMessage());
		}
	}
	
	/**
	 * Unit test of the validateUser method.
	 * @throws SQLException
	 */
	@Test
	void validateUserTest() throws SQLException {
		
		user.setUserName("testUName");
		user.setPassWord("testPW");
		user.setEmail("test@test.com");
		user.setPhone(658974123);
		user.setUserType("Consumer");
		
		try {
			userData.insertUser(user);
			assertNotNull(userData);
			System.out.println("Successfully inserted user data.");
		} catch (SQLException e) {
			System.out.println("Failed to insert user date" + e.getMessage());
		}
	}
	
	/**
	 * Unit test of the getUserCount method.
	 * @throws SQLException
	 */
    @Test
    void getUserCountTest() throws SQLException {
        try {
        	userData.getUserCount("testUName");
            assertNotNull(userData);
            System.out.println("Successfully retrieved user count.");
        } catch (SQLException e) {
            System.out.println("Failed to retrieve user count: " + e.getMessage());
        }
    }
    
    /**
     * Unit test of the resetUserCount method.
     * @throws SQLException
     */
    @Test
    void resetUserCountTest() throws SQLException {
        try {
        	userData.resetUserCount("testUName");
            int count = userData.getUserCount("testUName");
            assertEquals(0, count);
            System.out.println("Successfully reset user count.");
        } catch (SQLException e) {
            System.out.println("Failed to reset user count: " + e.getMessage());
        }
    }
}
