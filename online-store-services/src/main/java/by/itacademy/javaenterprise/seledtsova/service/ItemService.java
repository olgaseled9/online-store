package by.itacademy.javaenterprise.seledtsova.service;

import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.dto.ItemPageDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> getItems();

    ItemPageDTO findItemsWithPagination(int pageNumber, int pageSize);

    ItemDTO findItemById(Long id);

    void addItem(ItemDTO itemDTO);

    void removeItemById(Long id);

}

