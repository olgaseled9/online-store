package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.Item;

import java.util.List;

public interface ItemDao {

    Item findItemById(Long id);

    List<Item> findAll();
}
