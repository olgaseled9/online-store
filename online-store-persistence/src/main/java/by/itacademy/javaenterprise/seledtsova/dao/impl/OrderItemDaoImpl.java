package by.itacademy.javaenterprise.seledtsova.dao.impl;


import by.itacademy.javaenterprise.seledtsova.dao.OrderItemDao;
import by.itacademy.javaenterprise.seledtsova.entity.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDaoImpl extends GenericDaoImpl<Long, OrderItem> implements OrderItemDao {
}
