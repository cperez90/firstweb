package org.daw.firstweb.dao;

import jakarta.persistence.EntityManager;
import org.daw.firstweb.model.Movie;
import org.daw.firstweb.util.ConnectionManager;

import java.util.List;

public class MovieOrmDao implements MovieDao{

    @Override
    public List<Movie> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        List<Movie> movies;

        try {
            movies = em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("No se encontraron ningunas movies ", e);
        } finally {
            em.close();
        }

        return movies;
    }

    @Override
    public Movie findById(Long id) {

        try (EntityManager em = ConnectionManager.getEntityManager()) {
            return em.createQuery(
                            "SELECT m FROM Movie m LEFT JOIN FETCH m.comments c WHERE m.id = :id ORDER BY c.created_at ASC",
                            Movie.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("No se encontro la movie con el ID " + id, e);
        }
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
            throw new RuntimeException("No se pudo agregar la movie", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Movie updateMovie(Movie movie) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(movie);
            em.getTransaction().commit();
            return movie;
        }catch (Exception e){
            throw new RuntimeException("No se ha podido actualizar" , e);
        }finally {
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
