package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.UserDao;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import by.itacademy.javaenterprise.seledtsova.exception.DaoException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public User saveUser(User user) {
        if (user == null) throw new IllegalArgumentException();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            logger.error("Cannot save user" + e.getMessage(), e);
        }
        return user;
    }

    @Override
    public User findUserById(Long id) {
        User user = new User();
        try {
            user = entityManager.find(User.class, id);
        } catch (Exception e) {
            logger.error("Cannot find user by id" + e.getMessage(), e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.find(User.class, user.getId());
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            logger.error("Cannot update user" + e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            logger.error("Cannot delete user " + e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            String queryString = "from " + User.class.getName();
            Query query = entityManager.createQuery(queryString);
            return query.getResultList();
        } catch (DaoException e) {
            logger.error("Cannot get all users " + e.getMessage(), e);
        }
        return null;
    }
}
