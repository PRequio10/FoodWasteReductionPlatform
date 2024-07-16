<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<script type="text/javascript">
        function checkLogout() {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.get('logout') === 'success') {
                alert('Logout Successful');
            }
        }
    </script>
</head>
<body onload="checkLogout()">
    <header>
        <h1>Welcome to the Food Waste Reduction Platform</h1>
    </header>
    <div class="container">
        <section class="introduction">
            <h2>About Us</h2>
            <p>
                The Food Waste Reduction Platform is dedicated to minimizing food waste by connecting retailers, consumers, 
                and charitable organizations. Our platform allows retailers to list surplus food items, consumers to find 
                discounted food, and charitable organizations to claim donations.
            </p>
            <p>
                Our mission is to create a sustainable environment by reducing food waste and making surplus food available to 
                those in need. Join us in our efforts to promote food sustainability and support your community.
            </p>
        </section>
        <section class="welcome">
            <%
                String name = (String) session.getAttribute("name");
                if (name != null) {
            %>
                <p id="welcome-message">Welcome, <%= name %>!</p>
                <form action="myAccount.jsp" method="get" style="display:inline;">
                    <button type="submit">MyAccount</button>
                </form>
                <form action="logout" method="post" style="display:inline;">
                    <button type="submit">Logout</button>
                </form>
            <% } else { %>
                <form action="login.jsp" method="get" style="display:inline;">
                    <button type="submit">Login</button>
                </form>
            <% } %> 
        </section>
    </div>
    <footer>
        <p>&copy; 2024 Food Waste Reduction Platform. 8288 Section 040 Group 2.</p>
    </footer>
</body>
</html>