package fwrp.dao;

import java.sql.SQLException;
import fwrp.model.User;

public interface UserDAO {

	void insertUser(User user) throws SQLException;
	User validateUser(String email, String password) throws SQLException;
}
