package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.RoleDao;
import by.itacademy.javaenterprise.seledtsova.entity.Role;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import by.itacademy.javaenterprise.seledtsova.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);
    private static final String FIND_ROLE_BY_NAME_QUERY = "from Role as r where r.name=:name";

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Role findRoleById(Long id) {
        Role role = new Role();
        try {
            role = entityManager.find(Role.class, id);
        } catch (DaoException e) {
            logger.error("Cannot find role by id" + e.getMessage(), e);
        }
        return role;
    }

    @Override
    public Role findRoleByName(RoleType roleType) {
        try {
            Query query = entityManager.createQuery(FIND_ROLE_BY_NAME_QUERY);
            query.setParameter("name", roleType);
            return (Role) query.getSingleResult();
        } catch (DaoException e) {
            logger.error("Cannot find role by id" + e.getMessage(), e);
        }
        return null;
    }
}
