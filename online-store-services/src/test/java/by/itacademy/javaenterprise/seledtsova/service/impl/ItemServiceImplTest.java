package by.itacademy.javaenterprise.seledtsova.service.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.ItemServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dao.ItemDao;
import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @Mock
    ItemDao itemDao;
    @Mock
    ItemServiceConverter converter;
    @InjectMocks
    ItemServiceImpl itemService;


    @Test
    void shouldReturnItemsList() {
        ItemDTO itemDTO = new ItemDTO();
        Long id = 1L;
        itemDTO.setId(id);
        Item item = new Item();
        when(itemDao.findAll()).thenReturn(Collections.singletonList(item));
        when(converter.convertItemToDTO(item)).thenReturn(itemDTO);
        List<ItemDTO> items = itemService.getItems();
        assertEquals(items.get(0).getId(), itemDTO.getId());
    }

    @Test
    void shouldGetEmptyListOfItems() {
        List<ItemDTO> items = itemService.getItems();
        assertTrue(items.isEmpty());
    }


    @Test
    void shouldFindItemById() {
        ItemDTO itemDTO = new ItemDTO();
        Long id = 8L;
        Item item = new Item();
        item.setId(id);
        when(itemDao.findById(id)).thenReturn(item);
        when(converter.convertItemToDTO(item)).thenReturn(itemDTO);
        assertEquals(itemService.findItemById(item.getId()), itemDTO);
    }

}