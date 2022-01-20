package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.GenericDao;
import by.itacademy.javaenterprise.seledtsova.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDaoImpl<I, T> implements GenericDao<I, T> {

    private static final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

    @PersistenceContext
    @Qualifier(value = "entityManagerFactory")
    public EntityManager entityManager;
    public Class<T> entityClass;

    public GenericDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public void add(T entity) {
        try {
            entityManager.persist(entity);
        } catch (Exception e) {
            logger.error("Cannot save " + entity.getClass() + e.getMessage(), e);
        }
    }


    @Override
    public void delete(T entity) {
        try {
            entityManager.remove(entity);
        } catch (Exception e) {
            logger.error("Cannot delete " + entity.getClass() + e.getMessage(), e);
        }
    }

    @Override
    public T findById(I id) {
        try {
            return entityManager.find(entityClass, id);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(entityClass.getName() + " with id " + id + " was not found");
        }
    }

    @Override
    public void merge(T entity) {
        try {
            entityManager.merge(entity);
        } catch (Exception e) {
            logger.error("Cannot update " + entity.getClass() + e.getMessage(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        String queryString = "from " + entityClass.getName();
        Query query = entityManager.createQuery(queryString);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findWithPagination(int startPosition, int pageSize) {
        Query query = entityManager.createQuery("select t from " + entityClass.getName() + " t");
        query.setFirstResult(calculateOffset(startPosition, pageSize));
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public Long getCount() {
        Query query = entityManager.createQuery("select count (t.id) from " + entityClass.getName() + " t");
        return (Long) query.getSingleResult();
    }

    private int calculateOffset(int pageNumber, int pageSize) {
        return ((pageSize * pageNumber) - pageSize);
    }
}
