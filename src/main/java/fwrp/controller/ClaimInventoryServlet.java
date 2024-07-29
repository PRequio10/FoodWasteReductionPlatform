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

@WebServlet("/claim_inventory")
public class ClaimInventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private InventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        try {
            inventoryDAO = new InventoryDAOImpl();
        } catch (SQLException e) {
            throw new ServletException("Unable to initialize InventoryDAO", e);
        }
    }

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
