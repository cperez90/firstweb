package org.daw.firstweb.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.daw.firstweb.model.User;
import org.daw.firstweb.util.ConnectionManager;

public class UserOrmDao {
    public static User findByUsername(String username){
        User user;

        try (EntityManager em = ConnectionManager.getEntityManager()) {
            user = em.createQuery("SELECT u FROM User u where u.username = :username",User.class)
                    .setParameter("username",username)
                    .getSingleResult();
        }catch (NoResultException e) {
            user = null;
        }catch (Exception e) {
            throw new RuntimeException("No se encontro al user " + username, e);
        }
        return user;
    }
}
