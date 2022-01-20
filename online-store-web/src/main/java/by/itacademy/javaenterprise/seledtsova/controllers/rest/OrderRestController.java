package by.itacademy.javaenterprise.seledtsova.controllers.rest;

import by.itacademy.javaenterprise.seledtsova.dto.OrderShowDTO;
import by.itacademy.javaenterprise.seledtsova.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public List<OrderShowDTO> getAllOrders() {
        return orderService.getOrders();
    }
}
