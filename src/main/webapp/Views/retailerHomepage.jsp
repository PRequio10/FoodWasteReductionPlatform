<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
=======
<%@ page import="fwrp.dao.InventoryDAO" %>
<%@ page import="fwrp.dao.InventoryDAOImpl" %>
<%@ page import="fwrp.model.InventoryItem" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b
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
                <li>"User Name"</li>
<<<<<<< HEAD
                <li><a href="logout.php">Logout</a></li>
=======
                <li><a href="homepage.jsp">Logout</a></li>
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b
            </ul>
        </nav>
    </header>
    <main>
        <div class="container">
            <aside class="sidebar">
                <section>
                    <h2>Manage Inventory</h2>
                    
                    <%-- Add new items to inventory --%>
<<<<<<< HEAD
                    <form action="add_inventory" method="POST">
=======
                    <form action="<%= request.getContextPath() %>/add_inventory" method="POST">
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b
					    <label for="item_name">Item Name:</label>
					    <input type="text" id="item_name" name="item_name" required>
					    
					    <label for="quantity">Quantity:</label>
					    <input type="number" id="quantity" name="quantity" required>
					    
<<<<<<< HEAD
=======
					    <label for="price">Price:</label>
					    <input type="number" id="price" name="price" step="0.01" required>
					    
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b
					    <label for="expiry_date">Expiry Date:</label>
					    <input type="date" id="expiry_date" name="expiry_date" required>
					    
					    <label for="status">Status:</label>
					    <select id="status" name="status" required>
					        <option value="Available">Available</option>
					        <option value="Surplus">Surplus</option>
					        <option value="Claimed">Claimed</option>
					        <option value="Purchased">Purchased</option>
<<<<<<< HEAD
=======
					        <option value="Discount">Discount</option>
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b
					    </select>
					    
					    <button type="submit">Add Item</button>
					</form>
                    
                    <%-- Update Inventory using item ID --%>
<<<<<<< HEAD
                    <form action="update_inventory.php" method="POST">
=======
                    <form action= "<%= request.getContextPath() %>/update_inventory" method="POST">
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b
                        <label for="update_item_ID">Item ID:</label>
                        <input type="text" id="update_item_ID" name="update_item_ID" required>
                        
                        <label for="update_quantity">New Quantity:</label>
                        <input type="number" id="update_quantity" name="update_quantity" required>
                        
<<<<<<< HEAD
=======
                        <label for="update_price">New Price:</label>
                        <input type="number" id="update_price" name="update_price" step="0.01" required>
                        
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b
                        <label for="update_expiry_date">New Expiry Date:</label>
                        <input type="date" id="update_expiry_date" name="update_expiry_date" required>
                        
                        <label for="status">Status:</label>
                        <select id="status" name="status" required>
                            <option value="Available">Available</option>
                            <option value="Surplus">Surplus</option>
                            <option value="Claimed">Claimed</option>
                            <option value="Purchased">Purchased</option>
<<<<<<< HEAD
=======
                            <option value="Discount">Discount</option>
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b
                        </select>
                        
                        <button type="submit">Update Item</button>
                    </form>
                </section>

<<<<<<< HEAD
                <section>
                    <h2>List Surplus Food Items</h2>
                    <form action="list_surplus.php" method="POST">
                        <label for="surplus_item_ID">Surplus Item ID:</label>
                        <input type="text" id="surplus_item_ID" name="surplus_item_ID" required>
                        
                        <label for="listing_type">Listing Type:</label>
                        <select id="listing_type" name="listing_type" required>
                            <option value="donation">Donation</option>
                            <option value="sale">Sale at Discounted Price</option>
                        </select>
                        
                        <button type="submit">List Surplus Item</button>
                    </form>
                </section>
=======
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b

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
<<<<<<< HEAD
                                <%-- TABLE LOGIC FOR Surplus Flagging --%>
=======
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
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b
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
<<<<<<< HEAD
                                <th>Item Name</th>
                                <th>Quantity</th>
                                <th>Expiration Date</th>
                                <th>Status</th>
                                <th>Item ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%-- TABLE FOR CURRENT INVENTORY --%>
=======
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
>>>>>>> 01eeb0b870563ce5874da5465be64361727b622b
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