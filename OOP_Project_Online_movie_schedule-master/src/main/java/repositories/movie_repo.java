package repositories;

import models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class movie_repo {
    private Connection connection;

    public movie_repo(String url, String username, String password) throws Exception {
        connection = DriverManager. getConnection(url, username, password);
    }

    public List<Movie> getAllMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        Statement statement = connection.createStatement();
        String SQL_SELECT_MOVIES = "select * from movies_1 order by id asc";
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_MOVIES);
        while (resultSet.next()) {
            Movie movie = new Movie();
            movie.setId(resultSet.getInt("id"));
            movie.setName(resultSet.getString("name"));
            movie.setGenre(resultSet.getString("genre"));
            movie.setReleaseYear(resultSet.getInt("release_year"));
            movies.add(movie);
        }
        return movies;
    }

    public Movie getMovieById(int id) throws Exception {
        String SQL_SELECT_MOVIE_BY_ID = "SELECT * FROM movies_1 WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MOVIE_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Movie movie = new Movie();
                    movie.setId(result.getInt("id"));
                    movie.setName(result.getString("name"));
                    movie.setGenre(result.getString("genre"));
                    movie.setReleaseYear(result.getInt("release_year"));
                    return movie;
                }
            }
        }
        return null;
    }

    public String getMovieNameById(int id) throws Exception {
        String SQL_SELECT_MOVIE_BY_ID = "SELECT name FROM movies_1 WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MOVIE_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getString("name");
                }
            }
        }
        return null;
    }


}