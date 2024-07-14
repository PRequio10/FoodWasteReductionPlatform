package fwrp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import fwrp.dao.UserDAO;
import fwrp.dao.UserDAOImpl;
import fwrp.model.User;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registerUser")
public class RegisServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl(); 
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
        int phone = Integer.parseInt(request.getParameter("phone"));
        String userType = request.getParameter("user_type");

        // Create User object
        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setUserType(userType);

        try {
            
            userDAO.insertUser(user);

            
            request.getSession().setAttribute("username", username);

          
            response.sendRedirect("Views/registration-success.jsp");
        } catch (SQLException e) {
            
            throw new ServletException("Error inserting user", e);
        }
    }
}