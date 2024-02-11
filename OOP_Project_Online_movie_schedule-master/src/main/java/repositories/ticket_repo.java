package repositories;

import models.Ticket;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ticket_repo {
    private Connection connection;

    public ticket_repo(String url, String username, String password) throws Exception {
        connection = DriverManager.getConnection(url, username, password);
    }


    public List<Ticket> getTicketsById(int id) throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        String SQL_BY_SESSION_ID = "SELECT * FROM tickets WHERE session_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL_BY_SESSION_ID)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(result.getInt("id"));
                    ticket.setSessionId(result.getInt("session_id"));
                    ticket.setTicketId(result.getInt("ticket_id"));
                    ticket.setMovieName(result.getString("movie_name"));
                    ticket.setIsBooked(result.getBoolean("is_booked"));
                }
            }
        }
        return tickets;
    }
}
