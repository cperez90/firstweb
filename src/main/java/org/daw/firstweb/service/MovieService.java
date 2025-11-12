package org.daw.firstweb.service;

import org.daw.firstweb.model.Movie;

import java.util.ArrayList;
import java.util.List;

public interface MovieService {

    List<Movie> findAll();
    Movie findById(Long id);
    boolean addMovie(Movie newMovie);
    Movie deleteMovieById(Long id);
}
