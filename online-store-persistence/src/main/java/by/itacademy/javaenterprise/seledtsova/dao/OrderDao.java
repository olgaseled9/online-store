package by.itacademy.javaenterprise.seledtsova.dao;


import by.itacademy.javaenterprise.seledtsova.entity.Order;

public interface OrderDao extends GenericDao<Long, Order> {
    Long getCountOfItemsByOrderAndItemIds(Long orderId, Long itemId);

    Long getCountOfItemsByOrderId(Long id);

    Order findOrderByUsername(String username);
}
