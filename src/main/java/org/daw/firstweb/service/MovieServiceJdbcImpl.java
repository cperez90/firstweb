package org.daw.firstweb.service;

import org.daw.firstweb.model.Movie;
import org.daw.firstweb.util.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieServiceJdbcImpl implements MovieService{
    public static Connection connection;

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement("select * from movies");
            ResultSet result = pst.executeQuery();

            while(result.next()) {
                Long movieId = result.getLong("id");
                String movieTitle = result.getString("title");
                String movieDescription = result.getString("description");
                int movieYear = result.getInt("year");
                Movie movie = new Movie(movieId, movieTitle, movieDescription, movieYear,0);
                movies.add(movie);
            }

            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie findById(Long id) {
        String sql = "SELECT * FROM movies WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            try (ResultSet result = pst.executeQuery()) {
                if (result.next()) {
                    Long movieId = result.getLong("id");
                    String movieTitle = result.getString("title");
                    String movieDescription = result.getString("description");
                    int movieYear = result.getInt("year");

                    return new Movie(movieId, movieTitle, movieDescription, movieYear, 0);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addMovie(Movie newMovie) {
        String sql = "INSERT INTO movies (description, title, year) VALUES (?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setString(1, newMovie.getDescription());
            pst.setString(2, newMovie.getTitle());
            pst.setInt(3, newMovie.getYear());
            int affected = pst.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se insertó ninguna fila.");
            }
            return true;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    @Override
    public Movie deleteMovieById(Long id) {
        Movie movie = this.findById(id);
        if (movie == null) {
            return null;
        }
        String sql = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            int affected = pst.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se eliminó ninguna fila con id = " + id);
            }
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            connection = JdbcConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
