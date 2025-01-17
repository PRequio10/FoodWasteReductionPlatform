package fwrp.dao;

import fwrp.connection.DBConnection;
import fwrp.model.InventoryItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * The class implementing the InventoryItem data access object (DAO).
 *
 * @author John Vincent Doce
 */
public class InventoryDAOImpl implements InventoryDAO {
    private Connection connection;

    /**
     * Class constructor to initialized 
     * @throws SQLException
     */
    public InventoryDAOImpl() throws SQLException {
        this.connection = DBConnection.getInstance().getConnection();
    }
    
    /**
     * Adds inventory items to the database and adds +1 to the value of count column in Users Table
     * @throws SQLException
     * 
     */
    @Override
    public void addInventoryItem(InventoryItem item) throws SQLException {
        String sql = "INSERT INTO Inventory (item_name, quantity, expiration_date, status, price) VALUES (?, ?, ?, ?, ?)";
        String updateCountSql = "UPDATE Users SET count = count +1 WHERE user_type <> 'Retailer'";
        try (
        		PreparedStatement ps = connection.prepareStatement(sql);
        		PreparedStatement updateCountPs = connection.prepareStatement(updateCountSql)
        		) {
            ps.setString(1, item.getItemName());
            ps.setInt(2, item.getQuantity());
            ps.setDate(3, item.getExpirationDate());
            ps.setString(4, item.getStatus());
            ps.setDouble(5, item.getItemPrice());
            ps.executeUpdate();
            
            //Adding +1 to count column in Users Table
            updateCountPs.executeUpdate();
        }
    }

    /**
     * Updates inventory items in the database
     * @throws SQLException
     * 
     */
    @Override
    public void updateInventoryItem(InventoryItem item) throws SQLException {
        String sql = "UPDATE Inventory SET quantity = ?, expiration_date = ?, status = ?, price = ? WHERE item_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, item.getQuantity());
            ps.setDate(2, item.getExpirationDate());
            ps.setString(3, item.getStatus());
            ps.setDouble(4, item.getItemPrice());
            ps.setInt(5, item.getItemId());
            ps.executeUpdate();
        }
    }

    /**
     * Retrieves all inventory items to show in retailerHomepage, list updates automatically
     * @throws SQLException
     * 
     */
    @Override
    public List<InventoryItem> getAllInventoryItems() throws SQLException {
        List<InventoryItem> items = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                InventoryItem item = new InventoryItem();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setExpirationDate(rs.getDate("expiration_date"));
                item.setStatus(rs.getString("status"));
                item.setItemPrice(rs.getDouble("price"));
                items.add(item);
            }
        }
        return items;
    }
    
    /**
     * Retrieves all inventory items that are surplus to show in the charityHomepage
     * @throws SQLException
     */
	@Override
	public List<InventoryItem> getCharityItems() throws SQLException {
	    List<InventoryItem> items = new ArrayList<>();
	    String sql = "SELECT * FROM Inventory WHERE status = 'Surplus'";
	    try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            InventoryItem item = new InventoryItem();
	            item.setItemId(rs.getInt("item_id"));
	            item.setItemName(rs.getString("item_name"));
	            item.setQuantity(rs.getInt("quantity"));
	            item.setExpirationDate(rs.getDate("expiration_date"));
	            item.setStatus(rs.getString("status"));
	            items.add(item);
	        }
	    }
	    return items;
	}
	
	/**
	 * Retrieves all inventory items available for purchase to show in the cunsumerHomepage
	 * @throws SQLException
	 */
	@Override
	public List<InventoryItem> getConsumerItems() throws SQLException {
	    List<InventoryItem> items = new ArrayList<>();
	    String sql = "SELECT * FROM Inventory WHERE status = 'Available'";
	    try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            InventoryItem item = new InventoryItem();
	            item.setItemId(rs.getInt("item_id"));
	            item.setItemName(rs.getString("item_name"));
	            item.setQuantity(rs.getInt("quantity"));
	            item.setExpirationDate(rs.getDate("expiration_date"));
	            item.setStatus(rs.getString("status"));
	            item.setItemPrice(rs.getDouble("price"));
	            items.add(item);
	        }
	    }
	    return items;
	}
	
	/**
	 * Logic for consumer purchasing items via item ID 
	 * @throws SQLException
	 */
	@Override
	public void purchaseInventoryItem(InventoryItem item) throws SQLException {
	    String sql = "UPDATE Inventory SET status = 'Purchased' WHERE item_id = ?";
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	    	ps.setInt(1, item.getItemId());
	        ps.executeUpdate();
	    }
	}
	
	/**
	 * Logic for charity to claim surplus item via item ID
	 * @throws SQLException
	 */
	@Override
	public void claimInventoryItem(InventoryItem item) throws SQLException {
	    String sql = "UPDATE Inventory SET status = 'Claimed' WHERE item_id = ?";
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	    	ps.setInt(1, item.getItemId());
	        ps.executeUpdate();
	    }
	}

	/**
	 * Retrieves all inventory items that will be expired within 2 weeks from the current date to show in the retailerHomepage
	 * @throws SQLException
	 */
	@Override
    public List<InventoryItem> getSurplusFlagger() throws SQLException {
        List<InventoryItem> items = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate twoWeeksFromNow = today.plusWeeks(2);
        String sql = "SELECT * FROM Inventory WHERE expiration_date BETWEEN ? AND ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(today));
            ps.setDate(2, java.sql.Date.valueOf(twoWeeksFromNow));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InventoryItem item = new InventoryItem();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setExpirationDate(rs.getDate("expiration_date"));
                items.add(item);
            }
        }
        return items;
    }	
}
