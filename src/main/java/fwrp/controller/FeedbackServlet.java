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

@WebServlet("/submitFeedback")
public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
