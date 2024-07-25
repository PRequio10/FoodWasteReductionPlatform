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
                <li>"User Name"</li>
                <li><a href="logout.php">Logout</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <div class="container">
            <aside class="sidebar">
                <section>
                    <h2>Manage Inventory</h2>
                    
                    <%-- Add new items to inventory --%>
                    <form action="add_inventory" method="POST">
					    <label for="item_name">Item Name:</label>
					    <input type="text" id="item_name" name="item_name" required>
					    
					    <label for="quantity">Quantity:</label>
					    <input type="number" id="quantity" name="quantity" required>
					    
					    <label for="expiry_date">Expiry Date:</label>
					    <input type="date" id="expiry_date" name="expiry_date" required>
					    
					    <label for="status">Status:</label>
					    <select id="status" name="status" required>
					        <option value="Available">Available</option>
					        <option value="Surplus">Surplus</option>
					        <option value="Claimed">Claimed</option>
					        <option value="Purchased">Purchased</option>
					    </select>
					    
					    <button type="submit">Add Item</button>
					</form>
                    
                    <%-- Update Inventory using item ID --%>
                    <form action="update_inventory.php" method="POST">
                        <label for="update_item_ID">Item ID:</label>
                        <input type="text" id="update_item_ID" name="update_item_ID" required>
                        
                        <label for="update_quantity">New Quantity:</label>
                        <input type="number" id="update_quantity" name="update_quantity" required>
                        
                        <label for="update_expiry_date">New Expiry Date:</label>
                        <input type="date" id="update_expiry_date" name="update_expiry_date" required>
                        
                        <label for="status">Status:</label>
                        <select id="status" name="status" required>
                            <option value="Available">Available</option>
                            <option value="Surplus">Surplus</option>
                            <option value="Claimed">Claimed</option>
                            <option value="Purchased">Purchased</option>
                        </select>
                        
                        <button type="submit">Update Item</button>
                    </form>
                </section>

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
                                <%-- TABLE LOGIC FOR Surplus Flagging --%>
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
                                <th>Item Name</th>
                                <th>Quantity</th>
                                <th>Expiration Date</th>
                                <th>Status</th>
                                <th>Item ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%-- TABLE FOR CURRENT INVENTORY --%>
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