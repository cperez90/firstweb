package org.daw.firstweb.service;

import org.daw.firstweb.dto.MovieDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.NoSuchElementException;

public class MovieServiceStaticImpl implements MovieService{

    private static List<MovieDto> movies = new ArrayList<>();

    public List<MovieDto> findAll(){
        if (movies == null){
            throw new NoSuchElementException("No hay movies");
        }
        return movies;
    }

    @Override
    public MovieDto findById(Long id) {
        for (MovieDto movie : movies) {
            if (Objects.equals(movie.getId(), id)){
                return movie;
            }
        }
        throw new NoSuchElementException("No existe el id: " + id);
    }

    @Override
    public boolean addMovie(MovieDto newMovie) {
        if (movies.contains(newMovie)) {
            return false;
        } else {
            movies.add(newMovie);
            return true;
        }
    }

    @Override
    public MovieDto updateMovie(MovieDto movie) {
        return null;
    }

    @Override
    public MovieDto deleteMovieById(Long id){
        for (MovieDto movie : movies) {
            if (Objects.equals(movie.getId(), id)) {
                movies.remove(movie);
                return movie;
            }
        }
        return null;
    }
}
