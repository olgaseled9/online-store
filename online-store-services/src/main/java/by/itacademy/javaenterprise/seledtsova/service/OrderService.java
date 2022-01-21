package by.itacademy.javaenterprise.seledtsova.service;


import by.itacademy.javaenterprise.seledtsova.dto.ItemShowPageDTO;
import by.itacademy.javaenterprise.seledtsova.dto.OrderShowDTO;
import by.itacademy.javaenterprise.seledtsova.entity.StatusType;

import java.util.List;

public interface OrderService {

    List<OrderShowDTO> getOrders();

    void updateStatusByOrderId(Long id, StatusType statusType);

    ItemShowPageDTO findOrdersAndItemsWithPagination(int pageNumber, int pageSize);

    OrderShowDTO findOrderWithItemsByOrderId(Long id);

    void addItemToOrder(Integer itemsCount, Long itemId, String username);
}
