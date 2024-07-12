package fwrp.JUnit;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import fwrp.dao.UserDAOImpl;
import fwrp.model.User;

class UserDAOTest {

	@Test
	void test() throws SQLException {
		User user1 = new User();
		
		user1.setUserName("User1");
		user1.setPassWord("Pass");
		user1.setEmail("Email@email.com");
		user1.setPhone(123456789);
		user1.setUserType("Consumer");
		
		UserDAOImpl userReg = new UserDAOImpl();
		
		userReg.insertUser(user1);
		
	}

}
