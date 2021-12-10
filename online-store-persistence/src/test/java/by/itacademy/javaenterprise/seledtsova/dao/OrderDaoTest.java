package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.dao.impl.OrderDaoImpl;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class OrderDaoTest {

    private EntityManager entityManagerMock;
    private EntityTransaction entityTransactionMock;
    private OrderDao orderDao;

    @BeforeEach
    public void setUpBeforeEachTest() {
        entityManagerMock = Mockito.mock(EntityManager.class);
        entityTransactionMock = Mockito.mock(EntityTransaction.class);
        orderDao = new OrderDaoImpl(entityManagerMock);
    }

    @Test
    void shouldFindOrderByIdTest() {
        Order order = new Order();
        Long id = 1L;
        order.setId(id);
        when(entityManagerMock.find(Order.class, id)).thenReturn(order);
        Order order1 = orderDao.findOrderById(id);
        assertEquals(id, order1.getId());
    }

    @Test
    void shouldFindOrderWithWrongIdTest() {
        Long id = -1L;
        assertNull(orderDao.findOrderById(id));
    }
}
