package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.dao.impl.ItemDaoImpl;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class ItemDaoTest {

    private EntityManager entityManagerMock;
    private EntityTransaction entityTransactionMock;
    private ItemDao itemDao;

    @BeforeEach
    public void setUpBeforeEachTest() {
        entityManagerMock = Mockito.mock(EntityManager.class);
        entityTransactionMock = Mockito.mock(EntityTransaction.class);
        itemDao = new ItemDaoImpl(entityManagerMock);
    }

    @Test
    void shouldFindItemByIdTest() {
        Item item = new Item();
        Long id = 1L;
        item.setId(id);
        when(entityManagerMock.find(Item.class, id)).thenReturn(item);
        Item item1 = itemDao.findItemById(id);
        assertEquals(id, item.getId());
    }

    @Test
    void shouldFindItemWithWrongIdTest() {
        Long id = -1L;
        assertNull(itemDao.findItemById(id));
    }
}

