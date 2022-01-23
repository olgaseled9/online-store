package by.itacademy.javaenterprise.seledtsova.controllers.web;

import by.itacademy.javaenterprise.seledtsova.dto.ItemCountDTO;
import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.dto.ItemPageDTO;
import by.itacademy.javaenterprise.seledtsova.service.ItemService;
import by.itacademy.javaenterprise.seledtsova.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = {"/items"}, method = RequestMethod.GET)
    public String getAll(Model model) {
        List<ItemDTO> items = itemService.getItems();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/add-item")
    public String addItemPage(ItemDTO itemDTO, Model model) {
        return "add_item";
    }

    @PostMapping("/add-item")
    public String addNewItem(@ModelAttribute("itemDTO") @Valid ItemDTO itemDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            itemService.addItem(itemDTO);
            log.info("New item add successfully");
            return "redirect:/items";
        } else {
            log.error("Item not added");
            return "add_item";
        }
    }

    @GetMapping("/show-item-by-id")
    public String getItemById(@RequestParam("id") Long id, Model model) {
        ItemDTO item = itemService.findItemById(id);
        model.addAttribute("item", item);
        return "get_item_by_id";
    }

    @GetMapping("/add-item-to-order")
    public String getAddItemPage(@RequestParam("id") Long id, Model model,
                                 ItemCountDTO itemCountDTO) {
        itemCountDTO.setItemId(id);
        model.addAttribute("item", itemCountDTO);
        return "add_item_to_order_page";
    }

    @PostMapping("/add-item-to-order")
    public String AddItemToOrder(@ModelAttribute("itemCountDTO") ItemCountDTO itemCountDTO,
                                 BindingResult bindingResult,
                                 Principal principal) {
        if (bindingResult.hasErrors()) {
            return "error";
        } else {
            orderService.addItemToOrder(itemCountDTO.getItemCount(), itemCountDTO.getItemId(), principal.getName());
            return "redirect:/get";
        }
    }

    @GetMapping("/delete-item-by-id")
    public String removeItemById(@RequestParam("id") Long id) {
        if (id != null) {
            itemService.removeItemById(id);
            log.info("Item delete successfully");
        }
        return "redirect:/items";
    }

    @GetMapping("/get")
    public String getItems(Model model,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber
    ) {
        ItemPageDTO itemPage = itemService.findItemsWithPagination(pageNumber, pageSize);
        model.addAttribute("itemPage", itemPage);
        return "get_all_items";
    }
}
