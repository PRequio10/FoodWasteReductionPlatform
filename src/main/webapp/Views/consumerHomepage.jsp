<%@ page import="fwrp.dao.InventoryDAO" %>
<%@ page import="fwrp.dao.InventoryDAOImpl" %>
<%@ page import="fwrp.model.InventoryItem" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Consumer Homepage</title>
<link rel="stylesheet" type="text/css" href="consumerHomepage.css">
</head>

<body>
    <header>
        <h1>Consumer Dashboard</h1>
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
                <form action ="<%= request.getContextPath() %>/purchase_inventory" method="POST">
                    <label for="item_id">Item ID:</label>
                    <input type="text" id="item_id" name="item_id" required>
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
                                try {
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
                                } catch (Exception e) {
                                    e.printStackTrace();
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
