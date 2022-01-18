package by.itacademy.javaenterprise.seledtsova.dao.impl;


import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

@Log4j2
@Repository
public class OrderDaoImpl extends GenericDaoImpl<Long, Order> implements OrderDao {

    private static final String GET_ORDER_BY_USERNAME_QUERY = "select o from Order o join fetch o.user u where u.username = :user_name and o.status.id = :id";
    private static final String GET_COUNT_OF_ONE_ITEM_IN_ORDER_QUERY = "select count (i.id) from Order o join o.items i where o.id=:order_id and i.id=:item_id";
    private static final String GET_COUNT_OF_ALL_ITEMS_IN_ORDER = "select count (o) from Order o join o.items where o.id=:id";


    @Override
    public Long getCountOfItemsByOrderAndItemIds(Long orderId, Long itemId) {
        Query query = entityManager.createQuery(GET_COUNT_OF_ONE_ITEM_IN_ORDER_QUERY);
        query.setParameter("order_id", orderId);
        query.setParameter("item_id", itemId);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getCountOfItemsByOrderId(Long id) {
        Query query = entityManager.createQuery(GET_COUNT_OF_ALL_ITEMS_IN_ORDER);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    @Override
    public Order findOrderByUsername(String username) {
        Long id = 1L;
        Query query = entityManager.createQuery(GET_ORDER_BY_USERNAME_QUERY);
        query.setParameter("user_name", username);
        query.setParameter("id", id);
        try {
            return (Order) query.getSingleResult();
        } catch (NonUniqueResultException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
