package fwrp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fwrp.connection.DBConnection;

/**
 * FeedbackServlet is a servlet that handles the submission of feedback from users.
 * It processes POST requests and saves the feedback to the database.
 * If the email parameter is missing or empty, it redirects the user back to the feedback page with an error message.
 * Otherwise, it saves the feedback to the database and redirects the user to the homepage.
 * 
 * @author Shanklein Maruzandi Maninang
 */
@WebServlet("/submitFeedback")
public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP POST request for submitting feedback.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error is detected when the servlet handles the request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String feedback = request.getParameter("message");

        if (email == null || email.trim().isEmpty()) {
            response.sendRedirect("Views/feedback.jsp?error=Email is required");
            return;
        }

        try {
            DBConnection dbConnection = DBConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Feedback (email, feedback) VALUES (?, ?)")) {
                statement.setString(1, email);
                statement.setString(2, feedback);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("Error saving feedback", e);
        }

        response.sendRedirect("Views/homepage.jsp");
    }
}
