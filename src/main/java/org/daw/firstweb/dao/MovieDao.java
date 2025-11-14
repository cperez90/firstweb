package org.daw.firstweb.dao;

import org.daw.firstweb.model.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> findAll();
    Movie findById(Long id);
    boolean addMovie(Movie newMovie);
    Movie updateMovie(Movie movie);
    Movie deleteMovieById(Long id);
}
