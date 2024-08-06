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
import java.sql.Date;
import java.sql.SQLException;

/**
 * Servlet implementation class UpdateInventoryServlet.
 * This servlet handles the updating of inventory items.
 * @author John Vincent Doce
 */
@WebServlet("/update_inventory")
public class UpdateInventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private InventoryDAO inventoryDAO;

    /**
     * Initializes the servlet by setting up the InventoryDAO.
     *
     * @throws ServletException if an error occurs during initialization
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
     * Handles the HTTP POST method.
     * This method updates an inventory item based on the request parameters.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String itemIdStr = request.getParameter("update_item_ID");
        String quantityStr = request.getParameter("update_quantity");
        String expirationDateStr = request.getParameter("update_expiry_date");
        String status = request.getParameter("status");
        String priceStr = request.getParameter("update_price");
        try {
            int itemId = Integer.parseInt(itemIdStr);
            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);
            Date expirationDate = Date.valueOf(expirationDateStr);

            // Create InventoryItem object
            InventoryItem item = new InventoryItem(itemId, null, quantity, expirationDate, status, price);

            // Update the inventory item
            inventoryDAO.updateInventoryItem(item);

            // Redirect to the homepage or inventory list
            response.sendRedirect("Views/retailerHomepage.jsp");
        } catch (NumberFormatException e) {
            // Handle invalid number format
            throw new ServletException("Invalid number format: " + e.getMessage(), e);
        } catch (SQLException e) {
            // Handle SQL errors
            throw new ServletException("Database error while updating inventory item: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle other exceptions
            throw new ServletException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }
}
