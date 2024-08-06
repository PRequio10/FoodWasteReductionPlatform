<%@ page import="fwrp.dao.InventoryDAO" %>
<%@ page import="fwrp.dao.InventoryDAOImpl" %>
<%@ page import="fwrp.model.InventoryItem" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Retailer Homepage</title>
<link rel="stylesheet" type="text/css" href="retailerHomepage.css">
</head>
<body>
    <header>
    <h1>Retailer Dashboard</h1>
    <nav>
        <ul>
            <li>Hello, <%= session.getAttribute("username") %>!</li>
            <li><a href="homepage.jsp">Logout</a></li>
        </ul>
    </nav>
	</header>
	
    <main>
        <div class="container">
            <aside class="sidebar">
                <section>
                    <h2>Manage Inventory</h2>
                    
                    <%-- Add new items to inventory --%>
                    <form action="<%= request.getContextPath() %>/add_inventory" method="POST">
					    <label for="item_name">Item Name:</label>
					    <input type="text" id="item_name" name="item_name" required>
					    
					    <label for="quantity">Quantity:</label>
					    <input type="number" id="quantity" name="quantity" required>
					    
					    <label for="price">Price:</label>
					    <input type="number" id="price" name="price" step="0.01" required>
					    
					    <label for="expiry_date">Expiry Date:</label>
					    <input type="date" id="expiry_date" name="expiry_date" required>
					    
					    <label for="status">Status:</label>
					    <select id="status" name="status" required>
					        <option value="Available">Available</option>
					        <option value="Surplus">Surplus</option>
					        <option value="Claimed">Claimed</option>
					        <option value="Purchased">Purchased</option>
					        <option value="Discount">Discount</option>
					    </select>
					    
					    <button type="submit">Add Item</button>
					</form>
                    
                    <%-- Update Inventory using item ID --%>
                    <form action= "<%= request.getContextPath() %>/update_inventory" method="POST">
                        <label for="update_item_ID">Item ID:</label>
                        <input type="text" id="update_item_ID" name="update_item_ID" required>
                        
                        <label for="update_quantity">New Quantity:</label>
                        <input type="number" id="update_quantity" name="update_quantity" required>
                        
                        <label for="update_price">New Price:</label>
                        <input type="number" id="update_price" name="update_price" step="0.01" required>
                        
                        <label for="update_expiry_date">New Expiry Date:</label>
                        <input type="date" id="update_expiry_date" name="update_expiry_date" required>
                        
                        <label for="status">Status:</label>
                        <select id="status" name="status" required>
                            <option value="Available">Available</option>
                            <option value="Surplus">Surplus</option>
                            <option value="Claimed">Claimed</option>
                            <option value="Purchased">Purchased</option>
                            <option value="Discount">Discount</option>
                        </select>
                        
                        <button type="submit">Update Item</button>
                    </form>
                </section>


                <section>
                    <h2>Surplus Food Identification</h2>
                    <p>Items nearing expiration or in excess of demand will be flagged here:</p>
                    <div id="surplus_items">
                        <table>
                            <thead>
                                <tr>
                                    <th>Item Name</th>
                                    <th>Quantity</th>
                                    <th>Expiration Date</th>
                                    <th>Item ID</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                	InventoryDAO inventoryDAO = new InventoryDAOImpl();
                                    List<InventoryItem> surplusItems = inventoryDAO.getSurplusFlagger();
                                    for (InventoryItem item : surplusItems) {
                                        out.println("<tr>");
                                        out.println("<td>" + item.getItemName() + "</td>");
                                        out.println("<td>" + item.getQuantity() + "</td>");
                                        out.println("<td>" + item.getExpirationDate() + "</td>");
                                        out.println("<td>" + item.getItemId() + "</td>");
                                        out.println("</tr>");
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </section>
            </aside>
            
            <div class="content">
                <h2>Current Inventory</h2>
                <div id="current_inventory">
                    <table>
                        <thead>
                            <tr>
                                <th>Item ID</th>
                                <th>Item Name</th>                                
                                <th>Quantity</th>
                                <th>Expiration Date</th>
                                <th>Status</th>
                                <th>Item Price/Quantity</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<InventoryItem> items = inventoryDAO.getAllInventoryItems();

                                for (InventoryItem item : items) {
                                    out.println("<tr>");
                                    out.println("<td>" + item.getItemId() + "</td>");
                                    out.println("<td>" + item.getItemName() + "</td>");                                    
                                    out.println("<td>" + item.getQuantity() + "</td>");
                                    out.println("<td>" + item.getExpirationDate() + "</td>");
                                    out.println("<td>" + item.getStatus() + "</td>");
                                    out.println("<td>" + item.getItemPrice() + "</td>");
                                    out.println("</tr>");
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>




        </div>
    </main>
    <footer>
        <p>&copy; 2024 Food Waste Reduction Platform</p>
    </footer>

</body>

</html>