package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.UserServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.RoleDao;
import by.itacademy.javaenterprise.seledtsova.dao.UserDao;
import by.itacademy.javaenterprise.seledtsova.dto.UserDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Role;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import by.itacademy.javaenterprise.seledtsova.exception.ServiceException;
import by.itacademy.javaenterprise.seledtsova.exception.UserAlreadyExistException;
import by.itacademy.javaenterprise.seledtsova.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final UserServiceConverter converter;

    @Override
    @Transactional
    public List<UserDTO> getUsers() {
        List<User> users = userDao.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(converter.convertUserToDTO(user));
        }
        return userDTOS;
    }

    @Override
    @Transactional
    public void addUser(UserDTO userDTO) {
        if (usernameExist(userDTO.getUsername())) {
            logger.debug("User dont create!");
            throw new UserAlreadyExistException(String.format("User with userName=%s already exist", userDTO.getUsername()));
        } else {
            logger.debug("User create!");
            if (Objects.isNull(userDTO.getRole())) {
                userDTO.setRole(RoleType.ROLE_SALE_USER);
            }

            userDao.add(converter.convertDTOtoUser(userDTO));
        }
    }

    @Override
    @Transactional
    public User findUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (Objects.nonNull(user)) {
            return user;
        } else {
            throw new ServiceException(String.format("User with userName=%s not found ", username));
        }
    }

    @Override
    @Transactional
    public void updateUserRoleById(Long id, RoleType roleType) {
        User user = userDao.findById(id);
        if (Objects.nonNull(user)) {
            Role role = roleDao.findRoleByName(roleType);
            user.setRole(role);
        } else {
            throw new ServiceException(String.format("User with id=%s not found ", id));
        }
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        User user = userDao.findById(id);
        if (Objects.nonNull(user)) {
            userDao.delete(user);
        } else {
            throw new ServiceException(String.format("User with id=%s was not found", id));
        }
    }

    private boolean usernameExist(String username) {
        return userDao.findByUsername(username) != null;
    }
}


