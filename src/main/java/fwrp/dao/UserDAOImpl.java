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
		String insertUser = "INSERT INTO Users (username, password, email, phone, user_type, isSubscribed) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = dbConn.prepareStatement(insertUser)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassWord());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getPhone());
            preparedStatement.setString(5, user.getUserType());
            preparedStatement.setBoolean(6, user.getIsSubscribed());
            preparedStatement.executeUpdate();
        }
		catch( SQLException ex) {
			System.out.println(ex.getMessage());
		}

    }
	
	@Override
	public User validateUser(String email, String password) throws SQLException {
        String query = "SELECT user_id, username, password, email, phone, user_type FROM Users WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = dbConn.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id")); // Use 'user_id' instead of 'id'
                user.setUserName(rs.getString("username"));
                user.setPassWord(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getInt("phone"));
                user.setUserType(rs.getString("user_type"));
                return user;
            }
        }
        return null;
    }
}


