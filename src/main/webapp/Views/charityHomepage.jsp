<%@ page import="fwrp.dao.InventoryDAO" %>
<%@ page import="fwrp.dao.InventoryDAOImpl" %>
<%@ page import="fwrp.dao.UserDAO" %>
<%@ page import="fwrp.dao.UserDAOImpl" %>
<%@ page import="fwrp.model.InventoryItem" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Charitable Organization Homepage</title>
<link rel="stylesheet" type="text/css" href="charityHomepage.css">
</head>
    

<body>
    <header>
        <h1>Charity Dashboard</h1>
        <nav>
            <ul>
                <li>Hello, <%= session.getAttribute("username") %>!</li> 
                <li><a href="feedbackPage.jsp">Submit feedback</a></li>
                <li><a href="homepage.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <div class="container">
            <aside class="sidebar">
                <form action ="<%= request.getContextPath() %>/claim_inventory" method="POST">
                    <label for="item_id">Item ID:</label>
                    <input type="text" id="item_id" name="item_id" required>
                                        
                    <button type="submit">Claim Item</button>
                </form>
                
                <form action="<%= request.getContextPath() %>/reset_count" method="POST">
                 <%
                    String un = (String) session.getAttribute("username");
                    if (un != null) {
                            UserDAO userDAO = new UserDAOImpl();
                            int count = userDAO.getUserCount(un);
                            out.println("<p>Retailer has added " + count + " new item/s</p>");
                       }
                %> <button type="Submit"> Clear </button>
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
                                List<InventoryItem> items = inventoryDAO.getCharityItems();

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
