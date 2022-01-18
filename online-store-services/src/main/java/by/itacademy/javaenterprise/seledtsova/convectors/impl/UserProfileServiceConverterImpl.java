package by.itacademy.javaenterprise.seledtsova.convectors.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.UserProfileServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dto.UserProfileDTO;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserProfileServiceConverterImpl implements UserProfileServiceConverter {

    @Override
    public UserProfileDTO convertUserProfileToDTO(User user) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(user.getId());
        userProfileDTO.setFirstName(user.getFirstName());
        userProfileDTO.setLastName(user.getLastName());
        userProfileDTO.setPatronymic(user.getPatronymic());
        if (Objects.nonNull(user.getUserInformation())) {
            userProfileDTO.setEmail(user.getUserInformation().getEmail());
            userProfileDTO.setTelephone(user.getUserInformation().getTelephone());
        }
        return userProfileDTO;
    }
}
