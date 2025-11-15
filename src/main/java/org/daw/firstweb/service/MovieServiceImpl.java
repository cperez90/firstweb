package org.daw.firstweb.service;

import jakarta.persistence.EntityManager;
import org.daw.firstweb.dao.MovieDao;
import org.daw.firstweb.dao.MovieOrmDao;
import org.daw.firstweb.dto.MovieDto;
import org.daw.firstweb.model.Movie;
import org.daw.firstweb.util.ConnectionManager;
import org.daw.firstweb.util.MovieMapper;

import java.util.ArrayList;
import java.util.List;

public class MovieServiceImpl implements MovieService{
    MovieDao mvDao = new MovieOrmDao();

    @Override
    public List<MovieDto> findAll() {
        List<Movie> moviesDao = mvDao.findAll();
        List<MovieDto> moviesDto = new ArrayList<>();
        for (Movie mv : moviesDao){
            MovieDto mvdto = MovieMapper.mapToDto(mv);
            moviesDto.add(mvdto);
        }
        return moviesDto;
    }

    @Override
    public MovieDto findById(Long id) {
        Movie movieDao = mvDao.findById(id);
        return MovieMapper.mapToDto(movieDao);
    }

    @Override
    public boolean addMovie(MovieDto newMovie) {
        if (newMovie != null){
            Movie movie = MovieMapper.mapToDao(newMovie);
            mvDao.addMovie(movie);
            return true;
        }else return false;
    }


    @Override
    public MovieDto updateMovie(MovieDto movie) {
        Movie movieDao = MovieMapper.mapToDao(movie);
        mvDao.updateMovie(movieDao);
        return MovieMapper.mapToDto(mvDao.findById(movie.getId()));
    }

    @Override
    public MovieDto deleteMovieById(Long id) {
        Movie movieDao = mvDao.findById(id);
        mvDao.deleteMovieById(id);
        return MovieMapper.mapToDto(movieDao);
    }
}
