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
<title>Consumer Homepage</title>
<link rel="stylesheet" type="text/css" href="consumerHomepage.css">
</head>


<body>
    <header>
        <h1>Consumer Dashboard</h1>
        <nav>
            <ul>
                <li>"Username"</li>
                <li><a href="homepage.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <div class="container">
            <aside class="sidebar">
                <form action="claim_food.php" method="POST">
                    <label for="item_name">Item ID:</label>
                    <input type="text" id="item_name" name="item_name" required>
                    
                    
                    <button type="submit">Purchase</button>
                </form>
            </aside>
            
            <div class="content">
                <h2>Current Inventory</h2>
                <div id="current_inventory">
                    <table>
                        <thead>
                            <tr>
                                <th>Item Name</th>
                                <th>Quantity</th>
                                <th>Expiration Date</th>
                                <th>Status</th>
                                <th>Item ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                InventoryDAO inventoryDAO = new InventoryDAOImpl();
                                List<InventoryItem> items = inventoryDAO.getConsumerItems();

                                for (InventoryItem item : items) {
                                    out.println("<tr>");
                                    out.println("<td>" + item.getItemName() + "</td>");
                                    out.println("<td>" + item.getQuantity() + "</td>");
                                    out.println("<td>" + item.getExpirationDate() + "</td>");
                                    out.println("<td>" + item.getStatus() + "</td>");
                                    out.println("<td>" + item.getItemId() + "</td>");
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