package org.daw.firstweb.service;

import org.daw.firstweb.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.NoSuchElementException;

public class MovieServiceStaticImpl implements MovieService{

    private static List<Movie> movies = new ArrayList<>();

    public List<Movie> findAll(){
        if (movies == null){
            throw new NoSuchElementException("No hay movies");
        }
        return movies;
    }

    @Override
    public Movie findById(int id) {
        for (Movie movie : movies) {
            if (Objects.equals(movie.getId(), id)){
                return movie;
            }
        }
        throw new NoSuchElementException("No existe el id: " + id);
    }

    public boolean addMovie(Movie newMovie) {
        if (movies.contains(newMovie)) {
            return false;
        } else {
            movies.add(newMovie);
            return true;
        }
    }

    public Movie deleteMovieById(int id){
        for (Movie movie : movies) {
            if (Objects.equals(movie.getId(), id)) {
                movies.remove(movie);
                return movie;
            }
        }
        return null;
    }
}
