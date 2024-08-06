package fwrp.controller;

import fwrp.dao.UserDAO;
import fwrp.dao.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ResetCountServlet
 * 
 * This servlet handles the request to reset the user's count. It retrieves the username
 * from the session, resets the user's count in the database, and updates the session attribute.
 * @author John Vincent Doce
 * 
 */
@WebServlet("/reset_count")
public class ResetCountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * Handles the HTTP POST request to reset the user's count.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            try {
                UserDAO userDAO = new UserDAOImpl();
                userDAO.resetUserCount(username); // Add this method to your UserDAO
                session.setAttribute("count", 0); // Reset the count in the session
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the error appropriately
            }
        }

        response.sendRedirect("Views/charityHomepage.jsp");
    }
}
