package fwrp.dao;

import java.sql.SQLException;
import java.util.List;

import fwrp.model.InventoryItem;

/**
 * Data Access Object for the inventory
 * 
 * @author John Vincent Doce
 */
public interface InventoryDAO {
	 void addInventoryItem(InventoryItem item) throws Exception;
	 void updateInventoryItem(InventoryItem item) throws Exception;
	 List<InventoryItem> getAllInventoryItems() throws SQLException;
	 List<InventoryItem> getCharityItems() throws SQLException;
	 List<InventoryItem> getConsumerItems() throws SQLException;
	 List<InventoryItem> getSurplusFlagger() throws SQLException;
}
