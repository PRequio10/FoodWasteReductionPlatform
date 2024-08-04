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

@WebServlet("/Notif_count")
public class NotificationCountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

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
