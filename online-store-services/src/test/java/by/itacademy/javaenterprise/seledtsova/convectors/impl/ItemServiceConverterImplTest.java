package by.itacademy.javaenterprise.seledtsova.convectors.impl;

import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class ItemServiceConverterImplTest {

    @InjectMocks
    private ItemServiceConverterImpl itemServiceConverter;

    @Test
    void shouldConvertItemToDTOAndReturnCorrectId() {
        Item item = new Item();
        Long id = 1L;
        item.setId(id);
        ItemDTO itemDTO = itemServiceConverter.convertItemToDTO(item);
        Assertions.assertEquals(id, itemDTO.getId());
    }

    @Test
    void shouldConvertItemToDTOAndReturnCorrectName() {
        Item item = new Item();
        String name = "name";
        item.setName(name);
        ItemDTO itemDTO = itemServiceConverter.convertItemToDTO(item);
        Assertions.assertEquals(name, itemDTO.getName());
    }

    @Test
    void shouldConvertItemToDTOAndReturnCorrectDescription() {
        Item item = new Item();
        String description = "description";
        item.setDescription(description);
        ItemDTO itemDTO = itemServiceConverter.convertItemToDTO(item);
        Assertions.assertEquals(description, itemDTO.getDescription());
    }

    @Test
    void shouldConvertDTOToItemAndReturnName() {
        ItemDTO itemDTO = new ItemDTO();
        String name = "name";
        itemDTO.setName(name);
        Item item = itemServiceConverter.convertDTOtoItem(itemDTO);
        Assertions.assertEquals(name, item.getName());
    }

    @Test
    void shouldConvertDTOToItemAndReturnDescription() {
        ItemDTO itemDTO = new ItemDTO();
        String description = "description";
        itemDTO.setDescription(description);
        Item item = itemServiceConverter.convertDTOtoItem(itemDTO);
        Assertions.assertEquals(description, item.getDescription());
    }

    @Test
    void shouldConvertDTOToItemAndReturnPrice() {
        ItemDTO itemDTO = new ItemDTO();
        BigDecimal price = new BigDecimal("15.21323124124");
        itemDTO.setPrice(price);
        Item item = itemServiceConverter.convertDTOtoItem(itemDTO);
        Assertions.assertEquals(price, item.getPrice());
    }
}

