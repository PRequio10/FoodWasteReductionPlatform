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
 * Servlet implementation class NotificationCountServlet
 * 
 * This servlet is responsible for retrieving the notification count for a user and 
 * forwarding the request to the charity homepage view.
 * @author John Vincent Doce
 * 
 */
@WebServlet("/Notif_count")
public class NotificationCountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    /**
     * Initializes the servlet and sets up the UserDAO instance.
     *
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    /**
     * Handles the HTTP GET request.
     * Retrieves the username from the session and gets the user notification count.
     * The count is then set in the session and the request is forwarded to the charity homepage view.
     *
     * @param request the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            try {
                int count = userDAO.getUserCount(username);
                session.setAttribute("count", count);
            } catch (SQLException e) {
                throw new ServletException("Error retrieving user count", e);
            }
        }

        request.getRequestDispatcher("Views/charityHomepage.jsp").forward(request, response);
    }
}
