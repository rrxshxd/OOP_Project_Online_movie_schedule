package repositories;

import models.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class session_repo {
    private Connection connection;

    public session_repo(String url, String username, String password) throws Exception {
        connection = DriverManager.getConnection(url, username, password);
    }

    public List<Session> getSessionsByMovieId(int movieId) throws Exception {
        List<Session> sessions = new ArrayList<>();
        String SQL_SELECT_SESSIONS_BY_MOVIE_ID = "SELECT * FROM sessions WHERE movie_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SESSIONS_BY_MOVIE_ID)) {
            statement.setInt(1, movieId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Session session = new Session();
                    session.setId(result.getInt("id"));
                    session.setMovieId(result.getInt("movie_id"));
                    session.setSessionId(result.getInt("session_id"));
                    session.setMovieName(result.getString("movie_name"));
                    session.setTime(result.getTimestamp("time").toLocalDateTime());
                    sessions.add(session);
                }
            }
        }
        return sessions;
    }

    public int sessionCounter(String searchString) throws Exception {
        String SQL_SESSION_AMOUNT = "SELECT COUNT(*) FROM sessions WHERE movie_name = ?";
        int counter = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SESSION_AMOUNT)) {
            statement.setString(1, searchString);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    counter = result.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return counter;
    }
}