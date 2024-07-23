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

	@Test
	void userDAOtest() throws SQLException {
		
		User user = new User();
		user.setUserName("testUName");
		user.setPassWord("testPW");
		user.setEmail("test@test.com");
		user.setPhone(658974123);
		user.setUserType("Consumer");
		
		
		
	}

}
