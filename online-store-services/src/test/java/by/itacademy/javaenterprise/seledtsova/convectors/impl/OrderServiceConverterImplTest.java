package by.itacademy.javaenterprise.seledtsova.convectors.impl;

import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.dto.ItemShowDTO;
import by.itacademy.javaenterprise.seledtsova.dto.OrderShowDTO;
import by.itacademy.javaenterprise.seledtsova.entity.*;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class OrderServiceConverterImplTest {

    @Mock
    private OrderDao orderDao;
    @InjectMocks
    private OrderServiceConverterImpl orderServiceConverter;

    @Test
    void shouldConvertOrderToItemShowDTOAndReturnCorrectId() {
        Order order = new Order();
        Item item = new Item();
        Long testId = 1L;
        order.setId(testId);
        BigDecimal calculatedCost = BigDecimal.ZERO;
        item.setPrice(calculatedCost);
        ItemShowDTO itemShow = orderServiceConverter.convertOrderToItemShowDTO(order, item);
        Assertions.assertEquals(testId, itemShow.getOrderId());
   }

    @Test
    void shouldConvertOrderToItemShowDTOAndReturnCorrectDate() {
        Order order = new Order();
        Item item = new Item();
        order.setCreatedBy(LocalDate.now());
        BigDecimal calculatedCost = BigDecimal.ZERO;
        item.setPrice(calculatedCost);
        ItemShowDTO itemShow = orderServiceConverter.convertOrderToItemShowDTO(order, item);
        Assertions.assertEquals(order.getCreatedBy(), itemShow.getDate());
    }

    @Test
    void shouldConvertOrderToItemShowDTOAndReturnCorrectPrice() {
        Order order = new Order();
        Item item = new Item();
        BigDecimal calculatedCost = BigDecimal.ZERO;
        item.setPrice(calculatedCost);
        ItemShowDTO itemShow = orderServiceConverter.convertOrderToItemShowDTO(order, item);
        Assertions.assertEquals(calculatedCost, itemShow.getFinalPrice());
    }

    @Test
    void shouldConvertOrderToItemShowDTOAndReturnCorrectItemName() {
        Order order = new Order();
        Item item = new Item();
        BigDecimal calculatedCost = BigDecimal.ZERO;
        item.setPrice(calculatedCost);
        String name = "itemName";
        item.setName(name);
        ItemShowDTO itemShow = orderServiceConverter.convertOrderToItemShowDTO(order, item);
        Assertions.assertEquals(name, itemShow.getName());
    }

    @Test
    void shouldConvertOrderToOrderShowDTOAndReturnCorrectId() {
        Order order = new Order();
        Long testId = 1L;
        order.setId(testId);
        OrderShowDTO orderShow = orderServiceConverter.convertOrderToOrderShowDTO(order);
        Assertions.assertEquals(testId, orderShow.getId());
    }

    @Test
    void shouldConvertOrderToOrderShowDTOAndReturnCorrectStatus() {
        Order order = new Order();
        Status status = new Status();
        status.setName(StatusType.IN_PROGRESS);
        order.setStatus(status);
        OrderShowDTO orderShow = orderServiceConverter.convertOrderToOrderShowDTO(order);
        Assertions.assertEquals(status.getName(), orderShow.getStatusType());
    }

    @Test
    void shouldConvertOrderToOrderShowDTOAndReturnCorrectUserFirstName() {
        Order order = new Order();
        User user = new User();
        String firstName = "name";
        user.setFirstName(firstName);
        order.setUser(user);
        OrderShowDTO orderShow = orderServiceConverter.convertOrderToOrderShowDTO(order);
        Assertions.assertEquals(firstName, orderShow.getFirstName());
    }

    @Test
    void shouldConvertOrderToOrderShowDTOAndReturnCorrectUserLastName() {
        Order order = new Order();
        User user = new User();
        String lastName = "name";
        user.setLastName(lastName);
        order.setUser(user);
        OrderShowDTO orderShow = orderServiceConverter.convertOrderToOrderShowDTO(order);
        Assertions.assertEquals(lastName, orderShow.getLastName());
    }

    @Test
    void shouldConvertOrderToOrderShowDTOAndReturnCorrectUserTelephone() {
        Order order = new Order();
        User user = new User();
        UserInformation information = new UserInformation();
        String telephone = "+375293331112";
        information.setTelephone(telephone);
        user.setUserInformation(information);
        order.setUser(user);
        OrderShowDTO orderShow = orderServiceConverter.convertOrderToOrderShowDTO(order);
        Assertions.assertEquals(telephone, orderShow.getTelephone());
    }

    @Test
    void shouldConvertOrderToOrderShowDTOAndReturnCorrectItems() {
        Set<Item> items = new HashSet<>();
        Item item = new Item();
        BigDecimal calculatedCost = BigDecimal.ZERO;
        item.setPrice(calculatedCost);
        String name = "itemName";
        item.setName(name);
        items.add(item);
        Order order = new Order();
        order.setItems(items);
        OrderShowDTO orderShow = orderServiceConverter.convertOrderToOrderShowDTO(order);
        Assertions.assertEquals(name, orderShow.getItems().get(0).getName());
    }

    @Test
    void shouldConvertOrderToOrderShowDTOAndReturnCorrectFinalPrice() {
        Order order = new Order();
        Item item = new Item();
        BigDecimal calculatedCost = BigDecimal.ZERO;
        item.setPrice(calculatedCost);
        OrderShowDTO orderShow = orderServiceConverter.convertOrderToOrderShowDTO(order);
        Assertions.assertEquals(calculatedCost, orderShow.getFinalPrice());
    }
    @Test
    void shouldConvertOrderToItemShowDTOAndReturnCorrectStatus() {
        Order order = new Order();
        Item item = new Item();
        Status status = new Status();
        status.setName(StatusType.IN_PROGRESS);
        order.setStatus(status);
        BigDecimal calculatedCost = BigDecimal.ZERO;
        item.setPrice(calculatedCost);
        ItemShowDTO itemShow = orderServiceConverter.convertOrderToItemShowDTO(order, item);
        Assertions.assertEquals(status.getName(), itemShow.getStatusType());
    }
}