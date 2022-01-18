package by.itacademy.javaenterprise.seledtsova.convectors.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.OrderServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.dto.ItemForOrderDTO;
import by.itacademy.javaenterprise.seledtsova.dto.ItemShowDTO;
import by.itacademy.javaenterprise.seledtsova.dto.OrderShowDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderServiceConverterImpl implements OrderServiceConverter {

    private final OrderDao orderDao;

    @Override
    public OrderShowDTO convertOrderToOrderShowDTO(Order order) {
        OrderShowDTO orderShowDTO = new OrderShowDTO();
        orderShowDTO.setId(order.getId());
        if (Objects.nonNull(order.getStatus())) {
            orderShowDTO.setStatusType(order.getStatus().getName());
        }
        orderShowDTO.setItemsCount(orderDao.getCountOfItemsByOrderId(order.getId()));
        User user = order.getUser();
        if (Objects.nonNull(user)) {
            orderShowDTO.setFirstName(user.getFirstName());
            orderShowDTO.setLastName(user.getLastName());
            if (Objects.nonNull(user.getUserInformation())) {
                orderShowDTO.setTelephone(user.getUserInformation().getTelephone());
            }
        }
        List<ItemForOrderDTO> itemOrderDTOS = new ArrayList<>();
        if (Objects.nonNull(order.getItems())) {
            Set<Item> items = order.getItems();
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (Item item : items) {
                ItemForOrderDTO itemForOrder = new ItemForOrderDTO();
                itemForOrder.setName(item.getName());
                Long itemsCount = orderDao.getCountOfItemsByOrderAndItemIds(order.getId(), item.getId());
                itemForOrder.setCount(itemsCount);
                itemForOrder.setPrice(calculateCost(itemsCount, item.getPrice()));
                totalPrice = totalPrice.add(new BigDecimal(String.valueOf(itemForOrder.getPrice())));
                itemOrderDTOS.add(itemForOrder);
            }
            orderShowDTO.setItems(itemOrderDTOS);
            orderShowDTO.setFinalPrice(totalPrice);
        }
        return orderShowDTO;
    }

    @Override
    public ItemShowDTO convertOrderToItemShowDTO(Order order, Item item) {
        ItemShowDTO itemShow = new ItemShowDTO();
        itemShow.setName(item.getName());
        itemShow.setOrderId(order.getId());
        if (Objects.nonNull(order.getStatus())) {
            itemShow.setStatusType(order.getStatus().getName());
        }
        itemShow.setDate(order.getCreatedBy());
        Long itemsCount = orderDao.getCountOfItemsByOrderAndItemIds(order.getId(), item.getId());
        itemShow.setCount(itemsCount);
        itemShow.setFinalPrice(calculateCost(itemsCount, item.getPrice()));
        return itemShow;
    }

    private BigDecimal calculateCost(Long itemsCount, BigDecimal itemPrice) {
        BigDecimal bigDecimal = new BigDecimal(itemsCount);
        BigDecimal itemCost = itemPrice.multiply(bigDecimal);
        BigDecimal totalCost = BigDecimal.ZERO;
        return totalCost.add(itemCost);
    }
}
