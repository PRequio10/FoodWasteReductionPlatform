package fwrp.dao;

import java.sql.*;

import fwrp.connection.DBConnection;
import fwrp.model.SurplusFood;

/**
 * Implementation of SurplusDAO
 * 
 * @author John Philip William Requio
 */
public class SurplusDAOImpl implements SurplusDAO {
	
	public static Connection dbConn = null;
	
	public SurplusDAOImpl() {
		try {
			dbConn = DBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Unable to establish connection" + e.getMessage());
		}
	}

	/**
	 * For retailers to insert new food item in the database.
	 * @param SurplusFood object.
	 * @throws SQLException
	 * 
	 */
	@Override
	public void insertFood(SurplusFood cFood) throws SQLException {
		String insert = "INSERT INTO surplusfood (foodName, quantity, price, expDate) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = dbConn.prepareStatement(insert);
			stmt.setString(1, cFood.getFoodName());
			stmt.setInt(2, cFood.getQuantity());
			stmt.setInt(3, cFood.getPrice());
			stmt.setDate(4, cFood.getExpDate());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to insert" + e.getMessage());
		}
	}

	/**
	 * To display surplusfood table.
	 * @throws SQLException
	 */
	@Override
	public void foodTable() throws SQLException {
		String table = "SELECT * FROM surplusfood";
		try {
			PreparedStatement stmt = dbConn.prepareStatement(table);
			ResultSet rs = stmt.executeQuery();
			rs.getString("foodName");
			rs.getInt("quantity");
			rs.getInt("price");
			rs.getDate("expDate");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * To a data value of the surplusfood table.
	 * @param surplusfood object and foodid.
	 * @throws SQLException
	 */
	@Override
	public void updateFood(SurplusFood uFood, int foodID) throws SQLException {
		String update = "UPDATE surplusfood SET foodName = ?, quantity = ?, price = ?, expDate = ? WHERE foodID = ?";
		try {
			PreparedStatement stmt = dbConn.prepareStatement(update);
			stmt.setString(1, uFood.getFoodName());
			stmt.setInt(2, uFood.getQuantity());
			stmt.setInt(3, uFood.getPrice());
			stmt.setDate(4, uFood.getExpDate());
			stmt.setInt(5, foodID);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
