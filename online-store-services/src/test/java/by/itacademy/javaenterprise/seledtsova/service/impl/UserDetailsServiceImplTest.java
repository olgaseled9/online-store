package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.dto.UserLogin;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import by.itacademy.javaenterprise.seledtsova.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void shouldNotReturnEqualsUserLoginBecauseMethodReturnNew() {
        String userName = "ivan";
        User user = new User();
        user.setUsername(userName);
        UserLogin userLogin = new UserLogin(user);
        when(userService.findUserByUsername(userName)).thenReturn(user);
        assertNotEquals((userDetailsService.loadUserByUsername(user.getUsername())), userLogin);
    }
}
