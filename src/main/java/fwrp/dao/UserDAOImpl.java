package fwrp.dao;

import java.sql.*;
import java.sql.Connection;

import fwrp.connection.DBConnection;
import fwrp.model.User;

public class UserDAOImpl implements UserDAO{
	
	
	public Connection getConnection() throws SQLException {
		DBConnection instance = DBConnection.getInstance();
		Connection conn = instance.getConnection();
		
		return conn;
	}
	
	@Override
	public void insertUser(User user) throws SQLException {
		String insertUser = "INSERT INTO Users (username, password, email, phone, user_type) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertUser)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassWord());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getPhone());
            preparedStatement.setString(5, user.getUserType());
            preparedStatement.executeUpdate();
        }
		catch( SQLException ex) {
			System.out.println(ex.getMessage());
		}

    }
		
}


