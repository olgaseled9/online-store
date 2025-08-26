package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

@Log4j2
@Repository
public class OrderDaoImpl extends GenericDaoImpl<Long, Order> implements OrderDao {

    private static final String GET_ORDER_BY_USERNAME_AND_STATUS_CODE =
            "select o from Order o join fetch o.user u where u.username = :user_name";
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
        List<Order> list = entityManager.createQuery(GET_ORDER_BY_USERNAME_AND_STATUS_CODE, Order.class)
                .setParameter("user_name", username)
                .getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Order> findWithPaginationByUsername(int page, int size, String username) {
        return entityManager.createQuery(
                        "select distinct o from Order o " +
                                "join fetch o.user u " +
                                "left join fetch o.items i " +
                                "where u.username = :username " +
                                "order by o.id desc", Order.class)
                .setParameter("username", username)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Long getCountByUsername(String username) {
        return entityManager.createQuery(
                        "select count(o) from Order o " +
                                "join o.user u " +
                                "where u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
    }

}
