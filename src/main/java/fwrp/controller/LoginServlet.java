package fwrp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import fwrp.dao.UserDAOImpl;
import fwrp.model.User;
import java.io.IOException;
import java.sql.SQLException;

/**
 * LoginServlet handles the login functionality for the application.
 * It validates user credentials and redirects to the appropriate homepage based on user type.
 * 
 * @author Shanklein Maruzandi Maninang
 *
 */
@WebServlet("/validateLogin")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * An instance of UserDAOImpl to interact with the user data.
     */
    private UserDAOImpl userLogin;

    /**
     * Initializes the servlet and the UserDAOImpl instance.
     *
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init() throws ServletException {
        userLogin = new UserDAOImpl();
    }

    /**
     * Handles GET requests by redirecting to the login page.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Views/login.jsp");
    }


    /**
     * Handles POST requests for user login.
     * Validates the user credentials and redirects to the appropriate homepage
     * based on the user type. If validation fails, redirects to the login page with an error message.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userLogin.validateUser(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("username", user.getUserName());  // Store the username in the session
                
                String userType = user.getUserType();
                if ("Retailer".equals(userType)) {
                    response.sendRedirect("Views/retailerHomepage.jsp");
                } else if ("Consumer".equals(userType)) {
                    response.sendRedirect("Views/consumerHomepage.jsp");
                } else if ("Charitable Organization".equals(userType)) {
                    response.sendRedirect("Views/charityHomepage.jsp");
                } else {
                    response.sendRedirect("Views/login.jsp?error=Invalid user type");
                }
            } else {
                response.sendRedirect("Views/login.jsp?error=Invalid email or password");
            }
        } catch (SQLException e) {
            throw new ServletException("Error validating user", e);
        }
    }
}