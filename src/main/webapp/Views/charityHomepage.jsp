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
                <li>"User Name"</li>
                <li><a href="logout.php">Logout</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <div class="container">
            <aside class="sidebar">
                <form action="claim_food.php" method="POST">
                    <label for="item_name">Item Name:</label>
                    <input type="text" id="item_name" name="item_name" required>
                    
                    <label for="quantity">Quantity:</label>
                    <input type="number" id="quantity" name="quantity" required>
                    
                    <button type="submit">Claim Item</button>
                </form>
            </aside>
            
            <<div class="content">
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
