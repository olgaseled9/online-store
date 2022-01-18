package by.itacademy.javaenterprise.seledtsova.convectors.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.UserServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.RoleDao;
import by.itacademy.javaenterprise.seledtsova.dto.UserDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Role;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserServiceConverterImpl implements UserServiceConverter {

    @Autowired
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPatronymic((user.getPatronymic()));
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        if (Objects.nonNull(user.getRole())) {
            RoleType roleType = (user.getRole().getName());
            userDTO.setRole(roleType);
        }
        return userDTO;
    }

    @Override
    public User convertDTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPatronymic(userDTO.getPatronymic());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if (Objects.nonNull(userDTO.getRole())) {
            Role role = roleDao.findRoleByName(userDTO.getRole());
            user.setRole(role);
        }
        return user;
    }
}
