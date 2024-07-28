<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<form action="validateLogin" method="post">
		<label for="email">Email:</label>
		<input type="email" id="email" name="email" required><br><br>
		<label for="password">Password:</label>
		<input type="passord" id="passord" name="password" required><br><br>
		<button type="submit">Login</button>
	</form>
	<form action="register.jsp" method="get">
		<button type="submit">Register</button>
	</form>

</body>
</html>