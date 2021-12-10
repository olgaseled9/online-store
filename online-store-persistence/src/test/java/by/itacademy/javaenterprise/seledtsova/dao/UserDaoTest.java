package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.dao.impl.UserDaoImpl;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class UserDaoTest {

    private EntityManager entityManagerMock;
    private EntityTransaction entityTransactionMock;
    private UserDao userDao;

    @BeforeEach
    public void setUpBeforeEachTest() {
        entityManagerMock = Mockito.mock(EntityManager.class);
        entityTransactionMock = Mockito.mock(EntityTransaction.class);
        userDao = new UserDaoImpl(entityManagerMock);
    }

    @Test
    void shouldFindUserByIdTest() {
        User user = new User();
        Long id = 1L;
        user.setId(id);
        when(entityManagerMock.find(User.class, id)).thenReturn(user);
        User user1 = userDao.findUserById(id);
        assertEquals(id, user1.getId());
    }

    @Test
    void shouldFindUserWithWrongIdTest() {
        Long id = -1L;
        assertNull(userDao.findUserById(id));
    }

    @Test
    public void shouldSaveUserTest() {
        Long id = 4L;
        User user = new User();
        user.setId(id);
        user.setFirstName("Vika");
        user.setLastName("Soroka");
        user.setPatronymic("Victorovna");
        when(entityManagerMock.find(User.class, id)).thenReturn(user);
        assertEquals(user.getId(), id);
    }

    @Test
    public void shouldDeleteUserTest() {
        Long id = 2L;
        User user = new User();
        user.setId(id);
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
        when(entityManagerMock.find(User.class, id)).thenReturn(user).thenReturn(null);
        userDao.deleteUser(id);
        assertNull(userDao.findUserById(id));
    }

    @Test
    void shouldUpdateUserTest() {
        Long id = 8L;
        User user = new User();
        user.setId(id);
        user.setFirstName("Elvis");
        user.setLastName("Presley");
        user.setPatronymic("Ivanov");
        user.setUsername("song");
        user.setPassword("song");
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
        when(entityManagerMock.find(User.class, id)).thenReturn(user);
        userDao.updateUser(user);
        assertEquals(user.getId(), id);
    }

    @After
    public void tearDown() throws Exception {
        entityManagerMock.close();
    }
}
