package by.itacademy.javaenterprise.seledtsova.convectors;

import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Item;

public interface ItemServiceConverter {

    ItemDTO convertItemToDTO(Item item);

    Item convertDTOtoItem(ItemDTO itemDTO);
}
