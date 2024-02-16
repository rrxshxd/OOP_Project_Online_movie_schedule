package repositories;

import models.Session;
import models.Ticket;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ticket_repo {
    private Connection connection;

    public ticket_repo(String url, String username, String password) throws Exception {
        connection = DriverManager.getConnection(url, username, password);
    }

    public List<Ticket> getTicketsById(int id, int movId) throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        String SQL_BY_SESSION_ID = "SELECT * FROM tickets WHERE session_id = ? AND movie_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL_BY_SESSION_ID)) {
            statement.setInt(1, id);
            statement.setInt(2, movId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(result.getInt("id"));
                    ticket.setSessionId(result.getInt("session_id"));
                    ticket.setTicketId(result.getInt("ticket_id"));
                    ticket.setMovieName(result.getString("movie_name"));
                    ticket.setIsBooked(result.getBoolean("is_booked"));
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }

    public boolean isTicketBooked(int tickId, int sesId, int movId) throws Exception {
        String SQL_CHECK_TICKET_BOOKED = "SELECT is_booked FROM tickets WHERE ticket_id = ? AND session_id = ? AND movie_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL_CHECK_TICKET_BOOKED)) {
            statement.setInt(1, tickId);
            statement.setInt(2, sesId);
            statement.setInt(3, movId);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getBoolean("is_booked");
                }
            }
        }
        return false;
    }

    public void bookTicketById(int tickId, int sessionId, int movId, String customerName) throws Exception {
        String SQL_BOOK_TICKET_BY_ID = "UPDATE tickets SET is_booked = true, booked_for = ? WHERE ticket_id = ? AND session_id = ? AND movie_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL_BOOK_TICKET_BY_ID)) {
            statement.setString(1, customerName);
            statement.setInt(2, tickId);
            statement.setInt(3, sessionId);
            statement.setInt(4, movId);
            statement.executeUpdate();
        }
    }

}
