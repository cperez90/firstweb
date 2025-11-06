package org.daw.firstweb.service;

import org.daw.firstweb.model.Movie;

import java.util.List;

public interface MovieService {
    public List<Movie> findAll();
    public Movie findBy(Long id);
    public boolean addMovie(Movie newMovie);
    public Movie deleteMovieById(Long id);
}
