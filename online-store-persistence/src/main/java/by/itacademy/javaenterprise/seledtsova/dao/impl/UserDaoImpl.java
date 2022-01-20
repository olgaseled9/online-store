package by.itacademy.javaenterprise.seledtsova.dao.impl;


import by.itacademy.javaenterprise.seledtsova.dao.UserDao;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Log4j2
@Repository
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

    private static final String FIND_USER_WITH_ROLE_BY_USERNAME_QUERY = "select u from User u join fetch u.role where u.username = :user_name";

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery(FIND_USER_WITH_ROLE_BY_USERNAME_QUERY);
        query.setParameter("user_name", username);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}

