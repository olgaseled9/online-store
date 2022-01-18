package by.itacademy.javaenterprise.seledtsova.convectors.impl;

import by.itacademy.javaenterprise.seledtsova.dto.UserProfileDTO;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import by.itacademy.javaenterprise.seledtsova.entity.UserInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceConverterImplTest {

    @InjectMocks
    private UserProfileServiceConverterImpl userProfileServiceConverter;

    @Test
    void shouldConvertUserToUserProfileDTOAndReturnCorrectId() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        UserProfileDTO profile = userProfileServiceConverter.convertUserProfileToDTO(user);
        Assertions.assertEquals(id, profile.getId());
    }

    @Test
    void shouldConvertUserToUserProfileDTOAndReturnCorrectFirstName() {
        String testFirstName = "Pavel";
        User user = new User();
        user.setFirstName(testFirstName);
        UserProfileDTO profile = userProfileServiceConverter.convertUserProfileToDTO(user);
        Assertions.assertEquals(testFirstName, profile.getFirstName());
    }

    @Test
    void shouldConvertUserToUserProfileDTOAndReturnCorrectLastName() {
        String testLastName = "Pavlov";
        User user = new User();
        user.setLastName(testLastName);
        UserProfileDTO profile = userProfileServiceConverter.convertUserProfileToDTO(user);
        Assertions.assertEquals(testLastName, profile.getLastName());
    }

    @Test
    void shouldConvertUserInformationToUserProfileDTOAndReturnCorrectAddress() {
        String telephone = "+375291122321";
        UserInformation userInformation = new UserInformation();
        userInformation.setTelephone(telephone);
        User user = new User();
        user.setUserInformation(userInformation);
        UserProfileDTO profile = userProfileServiceConverter.convertUserProfileToDTO(user);
        Assertions.assertEquals(telephone, profile.getTelephone());
    }

    @Test
    void shouldConvertUserInformationToUserProfileDTOAndReturnCorrectTelephone() {
        String email = "user@yandex.com";
        UserInformation userInformation = new UserInformation();
        userInformation.setEmail(email);
        User user = new User();
        user.setUserInformation(userInformation);
        UserProfileDTO profile = userProfileServiceConverter.convertUserProfileToDTO(user);
        Assertions.assertEquals(email, profile.getEmail());
    }
}
