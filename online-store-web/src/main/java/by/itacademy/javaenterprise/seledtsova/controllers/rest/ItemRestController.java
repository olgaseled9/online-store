package by.itacademy.javaenterprise.seledtsova.controllers.rest;

import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemRestController {

    private final ItemService itemService;

    @GetMapping("/items")
    public List<ItemDTO> getAllItems() {
        return itemService.getItems();
    }

    @PostMapping("/items")
    public ResponseEntity<Void> addNewItem(@RequestBody @Valid ItemDTO itemDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            itemService.addItem(itemDTO);
            log.debug("Added item with name {}", itemDTO.getName());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable Long id) {
        itemService.removeItemById(id);
        log.info("Removed item with id {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable Long id) {
        return itemService.findItemById(id);
    }
}
