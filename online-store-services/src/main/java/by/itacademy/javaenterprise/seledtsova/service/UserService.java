package by.itacademy.javaenterprise.seledtsova.service;

import by.itacademy.javaenterprise.seledtsova.dto.UserDTO;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import by.itacademy.javaenterprise.seledtsova.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getUsers();

    void addUser(UserDTO userDTO);

    User findUserByUsername(String username);

    void removeUserById(Long id);

    void updateUserRoleById(Long id, RoleType roleType);

}
