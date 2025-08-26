package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.ItemServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.ItemDao;
import by.itacademy.javaenterprise.seledtsova.dao.OrderItemDao;
import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.dto.ItemPageDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import by.itacademy.javaenterprise.seledtsova.entity.OrderItem;
import by.itacademy.javaenterprise.seledtsova.exception.ServiceException;
import by.itacademy.javaenterprise.seledtsova.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static by.itacademy.javaenterprise.seledtsova.service.impl.ServiceUtil.getNumbersOfPages;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final OrderItemDao orderItemDao;
    private final ItemServiceConverter converter;

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


    @Override
    @Transactional
    public void addItem(ItemDTO itemDTO) {
        itemDao.add(converter.convertDTOtoItem(itemDTO));
    }

    @Override
    @Transactional
    public ItemDTO findItemById(Long id) {
        Item item = itemDao.findById(id);
        if (Objects.nonNull(item)) {
            return converter.convertItemToDTO(item);
        } else {
            throw new ServiceException(String.format("Item is not found with id=%s", id));
        }
    }

    @Override
    @Transactional
    public void removeItemById(Long id) {
        Item item = itemDao.findById(id);
        if (Objects.nonNull(item)) {
            List<OrderItem> orderItems = orderItemDao.findAll();
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getItemId().equals(id)) {
                    orderItemDao.delete(orderItem);
                }
            }
            itemDao.delete(item);
        } else {
            throw new ServiceException(String.format("Item is not found with id=%s", id));
        }
    }

    @Override
    @Transactional
    public ItemPageDTO findItemsWithPagination(int pageNumber, int pageSize) {
        ItemPageDTO itemPage = new ItemPageDTO();
        List<Item> items = itemDao.findWithPagination(pageNumber, pageSize);
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(converter.convertItemToDTO(item));
        }
        itemDTOS.sort(Comparator.comparing(ItemDTO::getName));
        itemPage.getItems().addAll(itemDTOS);
        Long countOfArticles = itemDao.getCount();
        itemPage.setPagesCount(countOfArticles);
        List<Integer> numbersOfPages = getNumbersOfPages(pageSize, countOfArticles);
        itemPage.getNumbersOfPages().addAll(numbersOfPages);
        return itemPage;
    }
}
