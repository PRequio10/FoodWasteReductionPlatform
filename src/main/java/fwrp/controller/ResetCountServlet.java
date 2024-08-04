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

@WebServlet("/reset_count")
public class ResetCountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
