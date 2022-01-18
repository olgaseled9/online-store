package by.itacademy.javaenterprise.seledtsova.convectors;

import by.itacademy.javaenterprise.seledtsova.dto.UserDTO;
import by.itacademy.javaenterprise.seledtsova.entity.User;


public interface UserServiceConverter {
    UserDTO convertUserToDTO(User user);

    User convertDTOtoUser(UserDTO userDTO);
}
