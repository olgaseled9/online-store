package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.dao.impl.GenericDaoImpl;
import by.itacademy.javaenterprise.seledtsova.dto.UserLogin;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import by.itacademy.javaenterprise.seledtsova.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " was not found");
        }
        return new UserLogin(user);
    }
}
