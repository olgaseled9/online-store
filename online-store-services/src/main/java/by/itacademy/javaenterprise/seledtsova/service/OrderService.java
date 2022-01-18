package by.itacademy.javaenterprise.seledtsova.service;


import by.itacademy.javaenterprise.seledtsova.dto.ItemShowPageDTO;
import by.itacademy.javaenterprise.seledtsova.dto.OrderShowDTO;
import by.itacademy.javaenterprise.seledtsova.entity.StatusType;

import java.util.List;

public interface OrderService {
    ItemShowPageDTO findOrdersAndItemsWithPagination(int pageNumber, int pageSize);

    OrderShowDTO findOrderWithItemsByOrderId(Long id);

    List<OrderShowDTO> getOrders();

    void updateStatusByOrderId(Long id, StatusType statusType);

    void addItemToOrder(Integer itemsCount, Long itemId, String username);
}
