package by.itacademy.javaenterprise.seledtsova.convectors.impl;

import by.itacademy.javaenterprise.seledtsova.convectors.ItemServiceConverter;
import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ItemServiceConverterImpl implements ItemServiceConverter {

    @Override
    public ItemDTO convertItemToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setDescription(item.getDescription());
        return itemDTO;
    }

    @Override
    public Item convertDTOtoItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setDescription(itemDTO.getDescription());

        MultipartFile file = itemDTO.getImageFile();
        if (file != null && !file.isEmpty()) {
            try {
                item.setImageBlob(file.getBytes());
                item.setImageContentType(file.getContentType()); // image/jpeg, image/png и т.д.
            } catch (IOException e) {
                throw new RuntimeException("Error while reading image file", e);
            }
        }

        return item;
    }
}
