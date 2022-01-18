package by.itacademy.javaenterprise.seledtsova.dao;


import by.itacademy.javaenterprise.seledtsova.entity.User;

public interface UserDao extends GenericDao<Long, User> {

    User findByUsername(String username);
}
