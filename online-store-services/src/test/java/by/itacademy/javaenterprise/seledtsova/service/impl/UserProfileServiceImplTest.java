package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.UserProfileServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.UserDao;
import by.itacademy.javaenterprise.seledtsova.dto.UserProfileDTO;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceImplTest {

    @Mock
    private UserDao userDao;
    @Mock
    private UserProfileServiceConverter converter;
    @InjectMocks
    UserProfileServiceImpl userProfileService;

    @Test
    void shouldFindProfileById() {
        UserProfileDTO profileDTO = new UserProfileDTO();
        Long id = 10L;
        User user = new User();
        user.setId(id);
        when(userDao.findById(id)).thenReturn(user);
        when(converter.convertUserProfileToDTO(user)).thenReturn(profileDTO);
        assertEquals(userProfileService.getById(user.getId()), profileDTO);
    }

    @Test
    void shouldFindProfileByUsername() {
        UserProfileDTO profileDTO = new UserProfileDTO();
        String userName = "ivan@gmail.com";
        String firstName = "ivan";
        User user = new User();
        user.setUsername(userName);
        user.setFirstName(firstName);
        when(userDao.findByUsername(userName)).thenReturn(user);
        when(converter.convertUserProfileToDTO(user)).thenReturn(profileDTO);
        assertEquals(userProfileService.findUserProfileByUsername(user.getUsername()), profileDTO);
    }
}
