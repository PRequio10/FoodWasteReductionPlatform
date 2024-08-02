package fwrp.JUnitTest;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import fwrp.dao.UserDAOImpl;
import fwrp.model.User;



/**
 * DAO model unit testing.
 * 
 * @author John Philip William Requio
 */
class UserDAOTest {

	/**
	 * Test of the insertUser method.
	 * @throws SQLException
	 */
	@Test
	void userInserttest() throws SQLException {
		
		User user = new User();
		user.setUserName("testUName");
		user.setPassWord("testPW");
		user.setEmail("test@test.com");
		user.setPhone(658974123);
		user.setUserType("Consumer");
		
		UserDAOImpl insert = new UserDAOImpl();
		
		try {
			insert.insertUser(user);
			assertNotNull(insert);
			System.out.println("Successfully inserted user data.");
		} catch (SQLException e) {
			System.out.println("Failed to insert user date" + e.getMessage());
		}
		
	}
	
	/**
	 * Test of the validateUser method.
	 * @throws SQLException
	 */
	@Test
	void validateUserTest() throws SQLException {
		
		User user = new User();
		user.setUserName("testUName");
		user.setPassWord("testPW");
		user.setEmail("test@test.com");
		user.setPhone(658974123);
		user.setUserType("Consumer");
		
		UserDAOImpl insert = new UserDAOImpl();
		
		try {
			insert.insertUser(user);
			assertNotNull(insert);
			System.out.println("Successfully inserted user data.");
		} catch (SQLException e) {
			System.out.println("Failed to insert user date" + e.getMessage());
		}
		
	}

}
