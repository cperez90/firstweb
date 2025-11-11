package org.daw.firstweb.service;

import jakarta.persistence.EntityManager;
import org.daw.firstweb.model.Movie;
import org.daw.firstweb.util.ConnectionManager;

import java.util.List;

public class MovieServiceOrmImpl implements MovieService{
    @Override
    public List<Movie> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        List<Movie> movies;

        try {
            movies = em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }

        return movies;
    }

    @Override
    public Movie findById(int id) {
        EntityManager em = ConnectionManager.getEntityManager();

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
