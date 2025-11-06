package org.daw.firstweb.service;

import org.daw.firstweb.model.Movie;

import java.util.List;

public class MovieServiceStaticImpl  implements MovieService{

    private static List<Movie> movies;

    public List<Movie> findAll(){
        return movies;
    }

    public Movie findBy(Long id) {
        return new Movie();
    }

    public boolean addMovie(Movie newMovie) {
        return true;
    }

    public Movie deleteMovieById(Long id){
        return new Movie();
    }
}
