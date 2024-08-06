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
 * Servlet implementation class PurchaseInventoryServlet
 * Handles the purchasing of inventory items.
 * @author John Vincent Doce
 * 
 */
@WebServlet("/purchase_inventory")
public class PurchaseInventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private InventoryDAO inventoryDAO;

    /**
     * Initializes the servlet and sets up the InventoryDAO.
     * 
     * @throws ServletException if there is a problem initializing the InventoryDAO.
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
     * Handles POST requests to purchase an inventory item.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
            request.getRequestDispatcher("Views/consumerHomepage.jsp").forward(request, response);
            return;
        }

        try {
            int itemId = Integer.parseInt(itemIdStr);

            // Create InventoryItem object with the item ID
            InventoryItem item = new InventoryItem();
            item.setItemId(itemId);

            // Purchase the inventory item
            inventoryDAO.purchaseInventoryItem(item);

            // Redirect to the homepage or inventory list with success message
            response.sendRedirect(request.getContextPath() + "/Views/consumerHomepage.jsp?message=Item+purchased+successfully");
        } catch (NumberFormatException e) {
            // Handle invalid number format
            request.setAttribute("error", "Invalid Item ID format. Please enter a valid number.");
            request.getRequestDispatcher("Views/consumerHomepage.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle SQL errors
            throw new ServletException("Database error while updating inventory item: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle other exceptions
            throw new ServletException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }
}
