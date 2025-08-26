package by.itacademy.javaenterprise.seledtsova.dao;


import by.itacademy.javaenterprise.seledtsova.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Long, Order> {
    Long getCountOfItemsByOrderAndItemIds(Long orderId, Long itemId);

    Long getCountOfItemsByOrderId(Long id);

    Order findOrderByUsername(String username);

    List<Order> findWithPaginationByUsername(int page, int size, String username);
    Long getCountByUsername(String username);
}
