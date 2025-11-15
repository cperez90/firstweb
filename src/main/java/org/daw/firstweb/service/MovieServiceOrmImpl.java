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

public class MovieServiceOrmImpl implements MovieService{
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
        return false;
    }


    @Override
    public MovieDto updateMovie(MovieDto movie) {
        return null;
    }

    @Override
    public MovieDto deleteMovieById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        MovieDto movieDelete;
        try {
            em.getTransaction().begin();
            movieDelete = em.find(MovieDto.class,id);
            if (movieDelete != null){
                em.remove(movieDelete);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar la película con ID " + id, e);

        } finally {
            em.close();
        }
        return movieDelete;
    }
}
