package fwrp.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import fwrp.dao.UserDAOImpl;
import fwrp.model.User;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class RegisServlet.
 * This servlet handles user registration requests.
 * @author Shanklein Maruzandi Maninang
 */
@WebServlet("/register")
public class RegisServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAOImpl userReg;

    /**
     * Initializes the servlet and creates an instance of UserDAOImpl.
     * 
     * @throws ServletException if an error occurs during initialization.
     */
    @Override
	public void init() throws ServletException {
		userReg = new UserDAOImpl();
	}

    /**
     * Handles the HTTP GET request.
     * Redirects the user to the registration page.
     * 
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws ServletException if an error occurs while handling the request.
     * @throws IOException      if an input or output error occurs while handling the request.
     */
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
        String subscribe = request.getParameter("subscribe");
        boolean isSubscribed = false;
        
        if (subscribe!= null) {
        	isSubscribed = true;
        }
        else {
        	isSubscribed = false;
        }
        
       
        // Create User object
        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setUserType(userType);
        user.setIsSubscribed(isSubscribed);
        
        try {
        	userReg.insertUser(user);
            
        } catch (SQLException e) {
            
            throw new ServletException("Error inserting user", e);
        }
        response.sendRedirect("Views/homepage.jsp");
    }
}