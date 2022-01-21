package by.itacademy.javaenterprise.seledtsova.service.impl;


import by.itacademy.javaenterprise.seledtsova.convectors.OrderServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.dto.OrderShowDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    OrderDao orderDao;
    @Mock
    OrderServiceConverter converter;
    @InjectMocks
    OrderServiceImpl orderService;


    @Test
    void shouldReturnListOfOrder() {
        Long id = 10L;
        OrderShowDTO orderShowDTO = new OrderShowDTO();
        orderShowDTO.setId(id);
        Order order = new Order();
        when(orderDao.findAll()).thenReturn(Collections.singletonList(order));
        when(converter.convertOrderToOrderShowDTO(order)).thenReturn(orderShowDTO);
        List<OrderShowDTO> orders = orderService.getOrders();
        assertEquals(orders.get(0).getId(), orderShowDTO.getId());
    }

    @Test
    void shouldReturnEmptyListOfOrders() {
        List<OrderShowDTO> orders = orderService.getOrders();
        assertTrue(orders.isEmpty());
    }

    @Test
    void shouldFindOrderWithItemsByOrderId() {
        Long id = 10L;
        OrderShowDTO orderShowDTO = new OrderShowDTO();
        Order order = new Order();
        order.setId(id);
        when(orderDao.findById(id)).thenReturn(order);
        when(converter.convertOrderToOrderShowDTO(order)).thenReturn(orderShowDTO);
        assertEquals(orderService.findOrderWithItemsByOrderId(order.getId()), orderShowDTO);
    }

}