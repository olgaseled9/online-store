package by.itacademy.javaenterprise.seledtsova.controllers;

import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public String getAll(Model model) {
        List<ItemDTO> items = itemService.getItems();
        model.addAttribute("items", items);
        return "items";

    }
}