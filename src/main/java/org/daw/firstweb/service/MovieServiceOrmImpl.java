package org.daw.firstweb.service;

import jakarta.persistence.EntityManager;
import org.daw.firstweb.model.Movie;
import org.daw.firstweb.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
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
    public Movie findById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();

        Movie movie = em.find(Movie.class,id);
        em.close();

        return movie;
    }

    @Override
    public boolean addMovie(Movie newMovie) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            if (this.findById(newMovie.getId()) != null) {
                return false;
            }
            em.getTransaction().begin();
            em.persist(newMovie);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public Movie deleteMovieById(Long id) {
        return null;
    }
}
