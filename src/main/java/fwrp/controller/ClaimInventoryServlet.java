package fwrp.controller;

import fwrp.dao.InventoryDAO;
import fwrp.dao.InventoryDAOImpl;
import fwrp.model.InventoryItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * This servlet handles the claiming of an inventory item by a user.
 * It processes the request to claim an item and updates the inventory accordingly.
 * The servlet logs the received item ID, validates it, and performs the necessary
 * database operations to mark the item as claimed.
 * If successful, the user is redirected to the homepage with a success message.
 * In case of errors, appropriate error messages are set and forwarded to the homepage.
 * 
 * @see HttpServlet
 * @author John Vincent Doce
 */
@WebServlet("/claim_inventory")
public class ClaimInventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private InventoryDAO inventoryDAO;

    /**
     * Initializes the Inventory data access object (DAO).
     * This method is called by the servlet container to indicate to a servlet
     * that the servlet is being placed into service.
     * 
     * @throws ServletException if an SQL error occurs while initializing the DAO
     */
    @Override
    public void init() throws ServletException {
        try {
            inventoryDAO = new InventoryDAOImpl();
        } catch (SQLException e) {
            throw new ServletException("Unable to initialize InventoryDAO", e);
        }
    }
    
    /**
     * Processes POST requests to claim an inventory item.
     * 
     * The method performs the following steps:
     * Log the received parameter.
     * Handle the case where item_id is null or empty.
     * Create InventoryItem object with the item ID.
     * Claim the inventory item.
     * Redirect to the homepage or inventory list with success message.
     * 
     * @param request the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemIdStr = request.getParameter("item_id");

        // Log the received parameter
        System.out.println("Received item_id: " + itemIdStr);

        if (itemIdStr == null || itemIdStr.isEmpty()) {
            // Handle the case where item_id is null or empty
            request.setAttribute("error", "Item ID is missing or empty.");
            request.getRequestDispatcher("Views/charityHomepage.jsp").forward(request, response);
            return;
        }

        try {
            int itemId = Integer.parseInt(itemIdStr);

            // Create InventoryItem object with the item ID
            InventoryItem item = new InventoryItem();
            item.setItemId(itemId);

            // Claim the inventory item
            inventoryDAO.claimInventoryItem(item);

            // Redirect to the homepage or inventory list with success message
            response.sendRedirect(request.getContextPath() + "/Views/charityHomepage.jsp?message=Item+claimed+successfully");
        } catch (NumberFormatException e) {
            // Handle invalid number format
            request.setAttribute("error", "Invalid Item ID format. Please enter a valid number.");
            request.getRequestDispatcher("Views/charityHomepage.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle SQL errors
            throw new ServletException("Database error while updating inventory item: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle other exceptions
            throw new ServletException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }
}
