package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.ItemDao;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemDaoImplTest {

    @Mock
    private EntityManager entityManagerMock;
    @Mock
    private EntityTransaction entityTransactionMock;
    @InjectMocks
    private ItemDaoImpl itemDao;

    @Test
    void shouldFindItemByIdTest() {
        Item item = new Item();
        Long id = 1L;
        item.setId(id);
        when(entityManagerMock.find(Item.class, id)).thenReturn(item);
        Item item1 = itemDao.findById(id);
        assertEquals(id, item1.getId());
    }

    @Test
    void shouldFindItemWithWrongIdTest() {
        Long id = -1L;
        assertNull(itemDao.findById(id));
    }
}