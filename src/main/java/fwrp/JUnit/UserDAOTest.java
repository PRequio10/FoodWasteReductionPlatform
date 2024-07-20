package fwrp.JUnit;

import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import fwrp.dao.UserDAOImpl;
import fwrp.model.User;
import junit.framework.TestFailure;

class UserDAOTest {

	@Test
	void daoConnectiontest() throws SQLException {
		
		UserDAOImpl userReg = new UserDAOImpl();
		
		assertNotNull(userReg, "DB Connection not null");
		
	}

}
