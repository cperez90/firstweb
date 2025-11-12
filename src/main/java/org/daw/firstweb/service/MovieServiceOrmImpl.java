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
        EntityManager em = ConnectionManager.getEntityManager();
        Movie movieDelete;
        try {
            em.getTransaction().begin();
            movieDelete = em.find(Movie.class,id);
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
