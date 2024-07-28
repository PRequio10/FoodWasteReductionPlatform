<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
	<h1>Register</h1>
	<form action="registerUser" method="post">
		<label for="name">Name:</label>
		<input type="text" id="name" name="name" required><br><br>
		<label for="email">Email:</label>
		<input type="email" id="email" name="email" required><br><br>
		<label for="password">Password:</label>
		<input type="password" id="password" name="password" required><br><br>
		<label for="user_type">User Type:</label>
		<select id="user_type" name="user_type" required>
			<option value="Retailer">Retailer</option>
			<option value="Consumer">Consumer</option>
			<option value="Charitable Organization">Charitable Organization</option>
		</select><br><br>
		<button type="submit">Register</button>
	</form>

</body>
</html>