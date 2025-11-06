package org.daw.firstweb.service;

import org.daw.firstweb.model.Movie;

import java.util.List;

public class MovieServiceStaticImpl  implements MovieService{

    private static List<Movie> movies;



    public List<Movie> findAll(){
        return movies;
    }

    public Movie findBy(Long id) {
        for (Movie movie : movies) {
            if (movie.getId() == id){
                return movie;
            }
        }
        return null;
    }

    public boolean addMovie(Movie newMovie) {
        if (movies.contains(newMovie)) {
            return false;
        } else {
            movies.add(newMovie);
            return true;
        }
    }

    public Movie deleteMovieById(Long id){
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movies.remove(movie);
                return movie;
            }
        }
        return null;
    }
}
