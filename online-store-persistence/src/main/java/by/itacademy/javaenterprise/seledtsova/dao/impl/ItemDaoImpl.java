package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.ItemDao;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ItemDaoImpl extends GenericDaoImpl<Long, Item> implements ItemDao {


}
