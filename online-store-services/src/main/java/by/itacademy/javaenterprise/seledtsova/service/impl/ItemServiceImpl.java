package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.ItemServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.ItemDao;
import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import by.itacademy.javaenterprise.seledtsova.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
 //@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemDao itemDao;
    @Autowired
    private final ItemServiceConverter converter;

    public ItemServiceImpl(ItemDao itemDao, ItemServiceConverter converter) {
        this.itemDao = itemDao;
        this.converter = converter;
    }

    @Override
    @Transactional
    public List<ItemDTO> getItems() {
        List<Item> items = itemDao.findAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(converter.convertItemToDTO(item));
        }
        return itemDTOS;
    }
}
