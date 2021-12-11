package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.StatusDao;
import by.itacademy.javaenterprise.seledtsova.entity.Status;
import by.itacademy.javaenterprise.seledtsova.entity.StatusType;
import by.itacademy.javaenterprise.seledtsova.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class StatusDaoImpl implements StatusDao {

    private static final Logger logger = LoggerFactory.getLogger(StatusDaoImpl.class);
    private static final String FIND_STATUS_BY_NAME_QUERY = "from Status as s where s.name=:name";

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Status findStatusByName(StatusType statusType) {
        try {
            Query query = entityManager.createQuery(FIND_STATUS_BY_NAME_QUERY);
            query.setParameter("name", statusType);
            return (Status) query.getSingleResult();
        } catch (DaoException e) {
            logger.error("cannot find status by name" + e.getMessage(), e);
        }
        return null;
    }
}

