package by.itacademy.javaenterprise.seledtsova.convectors;

import by.itacademy.javaenterprise.seledtsova.dto.UserProfileDTO;
import by.itacademy.javaenterprise.seledtsova.entity.User;


public interface UserProfileServiceConverter {
    UserProfileDTO convertUserProfileToDTO(User user);
}
