package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.Order;

import java.util.List;

public interface OrderDao {

    Order findOrderById(Long id);

    List<Order> findAll();

    Order findOrderByUsername(String username);

}
