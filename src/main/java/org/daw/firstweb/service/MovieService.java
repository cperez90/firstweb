package org.daw.firstweb.service;

import org.daw.firstweb.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();
    MovieDto findById(Long id);
    boolean addMovie(MovieDto newMovie);
    MovieDto updateMovie(MovieDto movie);
    MovieDto deleteMovieById(Long id);
}
