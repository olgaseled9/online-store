package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
    private static final String GET_ORDER_BY_USERNAME_QUERY = "select o from Order o join fetch o.user u where u.username = :username and o.status.id = :id";

    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public Order findOrderById(Long id) {
        Order order = new Order();
        try {
            order = entityManager.find(Order.class, id);
        } catch (Exception e) {
            logger.error("Cannot find order by id" + e.getMessage(), e);
        }
        return order;
    }

    @Override
    public Order findOrderByUsername(String username) {
        Long id = 1L;
        Query query = entityManager.createQuery(GET_ORDER_BY_USERNAME_QUERY);
        query.setParameter("username", username);
        query.setParameter("id", id);
        try {
            return (Order) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Order> findAll() {
        try {
            String queryString = "from " + Order.class.getName();
            Query query = entityManager.createQuery(queryString);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Cannot get all orders " + e.getMessage(), e);
        }
        return null;
    }
}

