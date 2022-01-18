package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.UserProfileServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.UserDao;
import by.itacademy.javaenterprise.seledtsova.dto.UserProfileDTO;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import by.itacademy.javaenterprise.seledtsova.entity.UserInformation;
import by.itacademy.javaenterprise.seledtsova.exception.ServiceException;
import by.itacademy.javaenterprise.seledtsova.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserDao userDao;
    private final UserProfileServiceConverter converter;

    @Override
    @Transactional
    public UserProfileDTO getById(Long id) {
        User user = userDao.findById(id);
        if (Objects.nonNull(user)) {
            return converter.convertUserProfileToDTO(user);
        } else {
            throw new ServiceException(String.format("User was not found with id=", id));
        }
    }

    @Override
    @Transactional
    public UserProfileDTO findUserProfileByUsername(String name) {
        User user = userDao.findByUsername(name);
        if (Objects.nonNull(user)) {
            return converter.convertUserProfileToDTO(user);
        } else {
            throw new ServiceException(String.format("User was not found with name ", name));
        }
    }

    @Override
    @Transactional
    public void updateUserProfile(UserProfileDTO userProfileDTO) {
        User user = userDao.findById(userProfileDTO.getId());
        if (Objects.nonNull(user)) {
            user.setFirstName(userProfileDTO.getFirstName());
            user.setLastName(userProfileDTO.getLastName());
            user.setPatronymic(userProfileDTO.getPatronymic());
            if (Objects.nonNull(user.getUserInformation())) {
                user.getUserInformation().setEmail(userProfileDTO.getEmail());
                user.getUserInformation().setTelephone(userProfileDTO.getTelephone());
            } else {
                UserInformation userInformation = new UserInformation();
                userInformation.setEmail(userProfileDTO.getEmail());
                userInformation.setTelephone(userProfileDTO.getTelephone());
                user.setUserInformation(userInformation);
            }
        } else {
            throw new ServiceException(String.format("User was not found ", userProfileDTO.getId()));
        }
    }
}
