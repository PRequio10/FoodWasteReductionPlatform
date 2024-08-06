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
 * This servlet let the user add an item in the inventory.
 * 
 * @author John Vincent Doce
 */
@WebServlet("/add_inventory")
public class AddInventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private InventoryDAO inventoryDAO;

    /**
     * Initializes the Inventory data access object (DAO).
     * 
     * @throws ServletException if unable to initialize InventoryDAO
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
     * Handles the HTTP POST request. Retrieves form parameters, creates an 
     * InventoryItem object, adds the item to the inventory, and redirects to 
     * the homepage to refresh the inventory list.
     * 
     * @param request the HttpServletRequest object that contains the request the client made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if an input or output error is detected when the servlet handles the request, or if an error occurs while adding the inventory item
     * @throws IOException if the request for the POST could not be handled
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String itemName = request.getParameter("item_name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date expirationDate = Date.valueOf(request.getParameter("expiry_date"));
        String status = request.getParameter("status");
        double price = Double.parseDouble(request.getParameter("price"));

        // Create InventoryItem object
        InventoryItem item = new InventoryItem(0, itemName, quantity, expirationDate, status, price);

        try {
            inventoryDAO.addInventoryItem(item);
        } catch (Exception e) {
            throw new ServletException("Error adding inventory item", e);
        }

        // Redirect to the homepage to refresh the inventory list
        response.sendRedirect("Views/retailerHomepage.jsp");
    }
}
