<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
</head>
<body>
	<h1> Welcome to the Food Waste Reduction Platform</h1>
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
        <section class="buttons">
			<%
				String email = (String) session.getAttribute("email");
				if (email != null) {
			%>
			<form action="logout" method="post">
				<button type="submit">Logout</button>
			</form>
			<% } else { %>
			<form action="login.jsp" method="get">
				<button type="submit">Login</button>
			</form>
			<% } %>
		</section>
	</div>
</body>
</html>