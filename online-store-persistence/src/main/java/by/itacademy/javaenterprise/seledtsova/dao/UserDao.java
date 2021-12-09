package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.User;

import java.util.List;

public interface UserDao {

    User saveUser(User user);

    User findUserById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    List<User> findAll();
}
