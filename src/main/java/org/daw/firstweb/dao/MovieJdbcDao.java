package org.daw.firstweb.dao;

import org.daw.firstweb.model.Movie;
import org.daw.firstweb.util.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieJdbcDao implements MovieDao{
    public static Connection connection;
    static {
        try {
            connection = JdbcConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


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
    public org.daw.firstweb.model.Movie findById(Long id) {
        String sql = "SELECT * FROM movies WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            try (ResultSet result = pst.executeQuery()) {
                if (result.next()) {
                    Long movieId = result.getLong("id");
                    String movieTitle = result.getString("title");
                    String movieDescription = result.getString("description");
                    int movieYear = result.getInt("year");

                    return new Movie(movieId, movieTitle, movieDescription, movieYear,0);
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
    public Movie updateMovie(Movie movie) {
        String sql = "UPDATE movies SET title = ?, description = ?, year = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1, movie.getTitle());
            pst.setString(2, movie.getDescription());
            pst.setInt(3,movie.getYear());
            pst.setLong(4,movie.getId());
            int updated = pst.executeUpdate();
            if (updated == 0){
                throw new RuntimeException("No se encontro la movie con Id "+movie.getId());
            }
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la movie",e);
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
}
