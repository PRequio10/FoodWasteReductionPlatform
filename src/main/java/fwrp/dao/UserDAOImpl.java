package fwrp.dao;

import java.sql.*;
import java.sql.Connection;

import fwrp.connection.DBConnection;
import fwrp.model.User;

public class UserDAOImpl implements UserDAO{
	
	public static Connection dbConn = null;
	
	public UserDAOImpl() {
		try { 
			dbConn = DBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Unable to establish connection" + e.getMessage());
		}
	}
	
	@Override
	public void insertUser(User user) throws SQLException {
		String insertUser = "INSERT INTO Users (username, password, email, phone, user_type) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = dbConn.prepareStatement(insertUser)) {
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


