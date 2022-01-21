package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.UserServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.UserDao;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;
    @Mock
    private UserServiceConverter converter;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldFindUserByUsername() {
        String userName = "ivan@gmail.com";
        User user = new User();
        user.setUsername(userName);
        when(userDao.findByUsername(userName)).thenReturn(user);
        assertEquals(userService.findUserByUsername(user.getUsername()), user);
    }
}
