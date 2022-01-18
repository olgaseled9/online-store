package by.itacademy.javaenterprise.seledtsova.convectors;

import by.itacademy.javaenterprise.seledtsova.dto.ItemShowDTO;
import by.itacademy.javaenterprise.seledtsova.dto.OrderShowDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import by.itacademy.javaenterprise.seledtsova.entity.Order;

public interface OrderServiceConverter {

    OrderShowDTO convertOrderToOrderShowDTO(Order order);

    ItemShowDTO convertOrderToItemShowDTO(Order order, Item item);
}
