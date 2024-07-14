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
</body>
</html>