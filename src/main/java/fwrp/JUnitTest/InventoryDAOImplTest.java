package fwrp.JUnitTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import fwrp.JUnitTest.*;
import fwrp.dao.InventoryDAOImpl;
import fwrp.model.InventoryItem;
/**
 * InventoryDAO unit testing.
 * 
 * @author John Philip William Requio
 */
class InventoryDAOImplTest {
	
	InventoryItem item = new InventoryItem();
	public InventoryDAOImpl invDAO() throws SQLException {
		return new InventoryDAOImpl();
	}
	
	/**
	 * Unit test of the addInventoryItem method.
	 * @throws SQLException
	 */
    @Test
    void testAddInventoryItem() throws SQLException {
        InventoryItem newItem = new InventoryItem();
        newItem.setItemName("item1");
        newItem.setQuantity(5);
        newItem.setExpirationDate(java.sql.Date.valueOf("2024-10-01"));
        newItem.setStatus("Available");
        newItem.setItemPrice(10.0);

        invDAO().addInventoryItem(newItem);

        List<InventoryItem> items = invDAO().getAllInventoryItems();
        int id = items.size();
        assertEquals(id, items.size());
        assertTrue(items.stream().anyMatch(item -> item.getItemName().equals("item1")));
    }
    
    /**
     * Unit test of the updateInventoryItem method.
     * @throws SQLException
     */
    @Test
    void testUpdateInventoryItem() throws SQLException {
        List<InventoryItem> items = invDAO().getAllInventoryItems();
        InventoryItem itemToUpdate = items.get(0);
        itemToUpdate.setQuantity(20);

        invDAO().updateInventoryItem(itemToUpdate);

        items = invDAO().getAllInventoryItems();
        int id = items.size();
        assertEquals(id, items.size());
        assertEquals(20, items.get(0).getQuantity());
    }
    
    /**
     * Unit test of the getAllInventoryItem method.
     * @throws SQLException
     */
    @Test
    void testGetAllInventoryItems() throws SQLException {
        List<InventoryItem> items = invDAO().getAllInventoryItems();
        int id = items.size();
        assertEquals(id, items.size());
        assertEquals("item1", items.get(0).getItemName());
    }
    
    /**
     * Unit test of the getCharityItem method.
     * @throws SQLException
     */
    @Test
    void testGetCharityItems() throws SQLException {
        InventoryItem surplusItem = new InventoryItem();
        surplusItem.setItemName("surplusItem");
        surplusItem.setQuantity(5);
        surplusItem.setExpirationDate(java.sql.Date.valueOf("2024-12-31"));
        surplusItem.setStatus("Surplus");
        surplusItem.setItemPrice(10.0);
        invDAO().addInventoryItem(surplusItem);

        List<InventoryItem> items = invDAO().getCharityItems();
        int id = items.size();
        assertEquals(id, items.size());
        assertEquals("surplusItem", items.get(0).getItemName());
    }
    
    /**
     * Unit test of the getConsumerItem method.
     * @throws SQLException
     */
    @Test
    void testGetConsumerItems() throws SQLException {
        List<InventoryItem> items = invDAO().getConsumerItems();
        int id = items.size();
        assertEquals(id, items.size());
        assertEquals("item1", items.get(0).getItemName());
    }
    
    /**
     * Unit test of the purchasedInventoryItem method.
     * @throws SQLException
     */
    @Test
    void testPurchaseInventoryItem() throws SQLException {
        List<InventoryItem> items = invDAO().getAllInventoryItems();
        InventoryItem itemToPurchase = items.get(0);

        invDAO().purchaseInventoryItem(itemToPurchase);

        items = invDAO().getAllInventoryItems();
        assertEquals("Purchased", items.get(0).getStatus());
    }
    
    /**
     * Unit test of the claimInventoryItem method.
     * @throws SQLException
     */
    @Test
    void testClaimInventoryItem() throws SQLException {
        InventoryItem surplusItem = new InventoryItem();
        surplusItem.setItemName("surplusItem");
        surplusItem.setQuantity(5);
        surplusItem.setExpirationDate(java.sql.Date.valueOf("2024-12-31"));
        surplusItem.setStatus("Surplus");
        surplusItem.setItemPrice(10.0);
        invDAO().addInventoryItem(surplusItem);

        List<InventoryItem> items = invDAO().getCharityItems();
        InventoryItem itemToClaim = items.get(0);

        invDAO().claimInventoryItem(itemToClaim);

        items = invDAO().getAllInventoryItems();
        assertEquals("Claimed", items.stream().filter(item -> item.getItemName().equals("surplusItem")).findFirst().get().getStatus());
    }
    
    /**
     * Unit test of the getSurplusFlagger method.
     * @throws SQLException
     */
    @Test
    void testGetSurplusFlagger() throws SQLException {
        InventoryItem soonToExpireItem = new InventoryItem();
        soonToExpireItem.setItemName("soonToExpireItem");
        soonToExpireItem.setQuantity(5);
        soonToExpireItem.setExpirationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(10)));
        soonToExpireItem.setStatus("Available");
        soonToExpireItem.setItemPrice(10.0);
        invDAO().addInventoryItem(soonToExpireItem);

        List<InventoryItem> items = invDAO().getSurplusFlagger();
        int id = items.size();
        assertEquals(id, items.size());
        assertEquals("soonToExpireItem", items.get(0).getItemName());
    }

}
