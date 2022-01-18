package by.itacademy.javaenterprise.seledtsova.convectors.impl;

import by.itacademy.javaenterprise.seledtsova.dto.UserDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Role;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceConverterImplTest {

    @InjectMocks
    private UserServiceConverterImpl userServiceConverter;

    @Test
    void shouldConvertUserToDTOAndReturnUserUsername() {
        User user = new User();
        String username = "user";
        user.setUsername(username);
        UserDTO userDTO = userServiceConverter.convertUserToDTO(user);
        Assertions.assertEquals(username, userDTO.getUsername());
    }

    @Test
    void shouldConvertUserToDTOAndReturnUserPassword() {
        User user = new User();
        String password = "user";
        user.setPassword(password);
        UserDTO userDTO = userServiceConverter.convertUserToDTO(user);
        Assertions.assertEquals(password, userDTO.getPassword());
    }

    @Test
    void shouldConvertUserToDTOAndReturnCorrectId() {
        User user = new User();
        Long testId = 1L;
        user.setId(testId);
        UserDTO userDTO = userServiceConverter.convertUserToDTO(user);
        Assertions.assertEquals(testId, userDTO.getId());
    }

    @Test
    void shouldConvertUserToDTOAndReturnCorrectRole() {
        Role role = new Role();
        User user = new User();
        role.setName(RoleType.ROLE_CUSTOMER_USER);
        user.setRole(role);
        UserDTO userDTO = userServiceConverter.convertUserToDTO(user);
        Assertions.assertEquals(role.getName(), userDTO.getRole());
    }

    @Test
    void shouldConvertUserToDTOAndReturnCorrectFirstName() {
        User user = new User();
        String firstName = "Pavel";
        user.setFirstName(firstName);
        UserDTO userDTO = userServiceConverter.convertUserToDTO(user);
        Assertions.assertEquals(firstName, userDTO.getFirstName());
    }

    @Test
    void shouldConvertUserToDTOAndReturnCorrectLastName() {
        User user = new User();
        String lastName = "Pavlov";
        user.setLastName(lastName);
        UserDTO userDTO = userServiceConverter.convertUserToDTO(user);
        Assertions.assertEquals(lastName, userDTO.getLastName());
    }
}
