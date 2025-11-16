package org.daw.firstweb.dao;

import jakarta.persistence.EntityManager;
import org.daw.firstweb.model.Comment;
import org.daw.firstweb.model.Movie;
import org.daw.firstweb.util.ConnectionManager;

import java.util.List;

public class CommentOrmDao implements CommentDao {

    @Override
    public List<Comment> findAllByMovieID(Long movieId) {
        EntityManager em = ConnectionManager.getEntityManager();
        List<Comment> comments;

        try {
            comments = em.createQuery("SELECT c FROM Comment c WHERE c.movie.id = :movieId ",Comment.class).setParameter("movieId",movieId).getResultList();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            em.close();
        }

        return comments;
    }

    @Override
    public Comment findById(Long id) {
        try (EntityManager em = ConnectionManager.getEntityManager()) {
            return em.find(Comment.class, id);
        } catch (Exception e) {
            throw new RuntimeException("No se encontro la movie", e);
        }
    }

    @Override
    public boolean addComment(Comment comment) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comment);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("No se pudo agregar el comment", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Comment updateComment(Comment comment) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(comment);
            em.getTransaction().commit();
            return comment;
        }catch (Exception e){
            throw new RuntimeException("No se ha podido actualizar" , e);
        }finally {
            em.close();
        }
    }

    @Override
    public Comment deleteCommentById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        Comment commentDelete;
        try {
            em.getTransaction().begin();
            commentDelete = em.find(Comment.class,id);
            if (commentDelete != null){
                em.remove(commentDelete);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar el comment con ID " + id, e);

        } finally {
            em.close();
        }
        return commentDelete;
    }
}
