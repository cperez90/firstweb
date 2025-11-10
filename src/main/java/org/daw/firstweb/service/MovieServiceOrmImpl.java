package org.daw.firstweb.service;

import jakarta.persistence.EntityManager;
import org.daw.firstweb.model.Movie;
import org.daw.firstweb.util.ConnectionManager;

import java.util.List;

public class MovieServiceOrmImpl implements MovieService{
    @Override
    public List<Movie> findAll() {
        EntityManager em = ConnectionManager.getEntityMabager();

        List<Movie> movies = em.createQuery("select m from Movie m",Movie.class);
        return List.of();
    }

    @Override
    public Movie findById(int id) {
        EntityManager em = ConnectionManager.getEntityMabager();

        Movie movie = em.find(Movie.class,id);
        em.close();

        return movie;
    }

    @Override
    public boolean addMovie(Movie newMovie) {
        return false;
    }

    @Override
    public Movie deleteMovieById(int id) {
        return null;
    }
}
