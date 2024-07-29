<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="homepage.css">
</head>
<body>
    <header>
        <h1>Login</h1>
    </header>
    <main>
        <form action="<%= request.getContextPath() %>/validateLogin" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>
            <button type="submit">Login</button>
        </form>
        <form action="registration.jsp" method="get">
            <button type="submit">Register</button>
        </form>

        <% 
        String error = request.getParameter("error");
        if (error != null) {
        %>
            <script type="text/javascript">
                window.onload = function() {
                    alert("Invalid email or password. Please try again.");
                }
            </script>
        <% 
        }
        %>
    </main>
    <footer>
        <p>&copy; 2024 Food Waste Reduction Platform</p>
    </footer>
</body>
</html>