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
        try {
            PreparedStatement pst = connection.prepareStatement("select * from movies where id = " + id);
            ResultSet result = pst.executeQuery();
            Long movieId = result.getLong("id");
            String movieTitle = result.getString("title");
            String movieDescription = result.getString("description");
            int movieYear = result.getInt("year");
            return result.next() ? new Movie(movieId,movieTitle,movieDescription,movieYear,0) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addMovie(Movie newMovie) {
        try {
            if (this.findById(newMovie.getId()) != null) {
                return false;
            } else {
                Connection conn = connection;
                String var10001 = newMovie.getTitle();
                conn.prepareStatement("insert into movies (title, description) values (" + var10001 + ", " + newMovie.getDescription() + ", " + newMovie.getYear() + ")");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie deleteMovieById(Long id) {
        Movie movie = this.findById(id);

        try {
            connection.prepareStatement("delete from movies where id = " + id);
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
