package by.itacademy.javaenterprise.seledtsova.dao;


import by.itacademy.javaenterprise.seledtsova.entity.Item;


public interface ItemDao extends GenericDao<Long, Item> {

    Item findById(Long id);

    byte[] findImageBlobById(Long id);

    String findImageContentTypeById(Long id);

    void updateImage(Long id, byte[] image, String contentType);
}