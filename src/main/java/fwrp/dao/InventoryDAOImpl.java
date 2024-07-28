package fwrp.dao;

import fwrp.connection.DBConnection;
import fwrp.model.InventoryItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {
    private Connection connection;

    public InventoryDAOImpl() throws SQLException {
        this.connection = DBConnection.getInstance().getConnection();
    }
    //Adds inventory items to the database
    @Override
    public void addInventoryItem(InventoryItem item) throws SQLException {
        String sql = "INSERT INTO Inventory (item_name, quantity, expiration_date, status, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, item.getItemName());
            ps.setInt(2, item.getQuantity());
            ps.setDate(3, item.getExpirationDate());
            ps.setString(4, item.getStatus());
            ps.setDouble(5, item.getItemPrice());
            ps.executeUpdate();
        }
    }
    //updates inventory items in the database
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

    //retrieves all inventory items to show in retailerHomepage, list updates automatically
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
    
    //retrieves all inventory items that are surplus to show in the charityHomepage
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
	//retrieves all inventory items available for purchase to show in the customerHomepage
	@Override
	public List<InventoryItem> getConsumerItems() throws SQLException {
	    List<InventoryItem> items = new ArrayList<>();
	    String sql = "SELECT * FROM Inventory WHERE status = 'Purchased'";
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
    
	//retrieves all inventory items that will be expired within 2 weeks from the current date to show in the retailerHomepage
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
