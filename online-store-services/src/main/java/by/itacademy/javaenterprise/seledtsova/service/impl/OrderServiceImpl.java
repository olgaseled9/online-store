package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.OrderServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.dao.OrderItemDao;
import by.itacademy.javaenterprise.seledtsova.dao.StatusDao;
import by.itacademy.javaenterprise.seledtsova.dao.UserDao;
import by.itacademy.javaenterprise.seledtsova.dto.ItemShowDTO;
import by.itacademy.javaenterprise.seledtsova.dto.ItemShowPageDTO;
import by.itacademy.javaenterprise.seledtsova.dto.OrderShowDTO;
import by.itacademy.javaenterprise.seledtsova.entity.*;
import by.itacademy.javaenterprise.seledtsova.exception.ServiceException;
import by.itacademy.javaenterprise.seledtsova.service.ItemService;
import by.itacademy.javaenterprise.seledtsova.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

import static by.itacademy.javaenterprise.seledtsova.service.impl.ServiceUtil.getNumbersOfPages;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final StatusDao statusDao;
    private final UserDao userDao;
    private final ItemService itemService;
    private final OrderServiceConverter converter;

    @Override
    @Transactional
    public List<OrderShowDTO> getOrders() {
        List<Order> ordersFromDataBase = orderDao.findAll();
        List<OrderShowDTO> orders = new ArrayList<>();
        for (Order order : ordersFromDataBase) {
            OrderShowDTO orderShowDTO = converter.convertOrderToOrderShowDTO(order);
            orders.add(orderShowDTO);
        }
        return orders;
    }

    @Override
    @Transactional
    public ItemShowPageDTO findOrdersAndItemsWithPagination(int pageNumber, int pageSize) {
        ItemShowPageDTO itemShowPage = new ItemShowPageDTO();
        List<Order> orders = orderDao.findWithPagination(pageNumber, pageSize);
        List<ItemShowDTO> itemShowDTOS = new ArrayList<>();
        for (Order order : orders) {
            if (Objects.nonNull(order.getItems())) {
                Set<Item> items = order.getItems();
                for (Item item : items) {
                    ItemShowDTO itemShow = converter.convertOrderToItemShowDTO(order, item);
                    itemShowDTOS.add(itemShow);
                }
            }
        }
        itemShowDTOS.sort(Comparator.comparing(ItemShowDTO::getDate).reversed());
        itemShowPage.getItems().addAll(itemShowDTOS);
        Long countOfOrders = orderDao.getCount();
        itemShowPage.setPagesCount(countOfOrders);
        List<Integer> numbersOfPages = getNumbersOfPages(pageSize, countOfOrders);
        itemShowPage.getNumbersOfPages().addAll(numbersOfPages);
        return itemShowPage;
    }

    @Override
    @Transactional
    public OrderShowDTO findOrderWithItemsByOrderId(Long id) {
        Order order = orderDao.findById(id);
        if (Objects.nonNull(order)) {
            return converter.convertOrderToOrderShowDTO(order);
        } else {
            throw new ServiceException(String.format("Order not found with id= %s", id));
        }
    }

    @Override
    @Transactional
    public void updateStatusByOrderId(Long id, StatusType statusType) {
        Order order = orderDao.findById(id);
        if (Objects.nonNull(order)) {
            Status status = statusDao.findStatusByName(statusType);
            order.setStatus(status);
        } else {
            throw new ServiceException(String.format("Order not found with id= %s", id));
        }
    }

    @Override
    @Transactional
    public void addItemToOrder(Integer itemsCount, Long itemId, String username) {
        Order orderFromDatabase = orderDao.findOrderByUsername(username);
        if (Objects.nonNull(orderFromDatabase) && orderFromDatabase.getStatus().getName().equals(StatusType.NEW)) {
            Long orderId = orderFromDatabase.getId();
            saveOrderItemByItemsCount(itemsCount, itemId, orderId);
        } else {
            Order order = new Order();
            order.setStatus(statusDao.findStatusByName(StatusType.NEW));
            order.setUser(userDao.findByUsername(username));
            order.setCreatedBy(LocalDate.now());
            orderDao.add(order);
            Long orderId = order.getId();
            saveOrderItemByItemsCount(itemsCount, itemId, orderId);
        }
    }

    private void saveOrderItemByItemsCount(Integer itemsCount, Long itemId, Long orderId) throws ServiceException {
        for (int i = 1; i <= itemsCount; i++) {
            OrderItem orderItem = new OrderItem();
            if (itemExist(itemId)) {
                orderItem.setItemId(itemId);
                orderItem.setOrderId(orderId);
                orderItemDao.add(orderItem);
            }
        }
    }

    @Override
    @Transactional
    public ItemShowPageDTO findOrdersAndItemsWithPaginationForUser(int pageNumber, int pageSize, String username) {
        ItemShowPageDTO itemShowPage = new ItemShowPageDTO();

        // DAO-метод должен уметь выбирать только заказы конкретного пользователя
        List<Order> orders = orderDao.findWithPaginationByUsername(pageNumber, pageSize, username);

        List<ItemShowDTO> itemShowDTOS = new ArrayList<>();
        for (Order order : orders) {
            if (order.getItems() != null) {
                for (Item item : order.getItems()) {
                    ItemShowDTO dto = converter.convertOrderToItemShowDTO(order, item);
                    itemShowDTOS.add(dto);
                }
            }
        }

        itemShowDTOS.sort(Comparator.comparing(ItemShowDTO::getDate).reversed());
        itemShowPage.getItems().addAll(itemShowDTOS);

        Long countOfOrders = orderDao.getCountByUsername(username);
        itemShowPage.setPagesCount(countOfOrders);

        List<Integer> numbersOfPages = getNumbersOfPages(pageSize, countOfOrders);
        itemShowPage.getNumbersOfPages().addAll(numbersOfPages);

        return itemShowPage;
    }


    private boolean itemExist(Long itemId) {
        return itemService.findItemById(itemId) != null;
    }
}
