package fwrp.controller;


//import jakarta.servlet.ServletConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import fwrp.dao.UserDAOImpl;
import fwrp.model.User;
import java.io.IOException;
import java.sql.*;

@WebServlet("/register")
public class RegisServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAOImpl userReg;

    @Override
	public void init() throws ServletException {
		userReg = new UserDAOImpl();
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Views/registration.jsp"); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String num = request.getParameter("phone");
        int phone = Integer.parseInt(num);
        String userType = request.getParameter("user_type");

        // Create User object
        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setUserType(userType);

        try {
        	userReg.insertUser(user);
            
        } catch (SQLException e) {
            
            throw new ServletException("Error inserting user", e);
        }
        response.sendRedirect("Views/homepage.jsp");
    }
}