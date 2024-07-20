package fwrp.JUnitTest;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import fwrp.dao.UserDAOImpl;
import fwrp.model.User;
import junit.framework.TestFailure;


/**
 * DAO model unit testing.
 * 
 * @author John Philip William Requio
 */
class UserDAOTest {

	@Test
	void daoConnectiontest() throws SQLException {
		User user = new User();
		UserDAOImpl userReg = new UserDAOImpl();
		
		user.setUserName(null);
		user.setPassWord(null);
		user.setEmail(null);
		user.setPhone(0);
		user.setUserType(null);
		
		
		
	}

}
