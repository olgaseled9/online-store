package by.itacademy.javaenterprise.seledtsova.controllers.web;

import by.itacademy.javaenterprise.seledtsova.dto.ItemShowPageDTO;
import by.itacademy.javaenterprise.seledtsova.dto.OrderShowDTO;
import by.itacademy.javaenterprise.seledtsova.dto.StatusDTO;
import by.itacademy.javaenterprise.seledtsova.entity.StatusType;
import by.itacademy.javaenterprise.seledtsova.service.OrderService;
import by.itacademy.javaenterprise.seledtsova.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final StatusService statusService;

    @GetMapping("/show")
    public String getItemsWithOrders(Model model,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                     @RequestParam(value = "page", defaultValue = "1") int pageNumber
    ) {
        ItemShowPageDTO itemPage = orderService.findOrdersAndItemsWithPagination(pageNumber, pageSize);
        model.addAttribute("itemPage", itemPage);
        return "get_all_orders_items";
    }

    @GetMapping("/show-order-by-id")
    public String getItemWithOrderByOrderId(@RequestParam Long id, Model model) {
        OrderShowDTO order = orderService.findOrderWithItemsByOrderId(id);
        model.addAttribute("order", order);
        return "get_order_by_id";
    }

    @GetMapping("/update-status")
    public String getUpdateStatusPage(@RequestParam Long id, Model model) {
        OrderShowDTO orderShowDTO = orderService.findOrderWithItemsByOrderId(id);
        model.addAttribute("orderShowDTO", orderShowDTO);
        List<StatusDTO> statuses = statusService.findAll();
        statuses.removeIf(status -> status.getName().equals(orderShowDTO.getStatusType()));
        model.addAttribute("statuses", statuses);
        return "update_order";
    }

    @PostMapping("/update-status")
    public String updateStatusByOrderId(OrderShowDTO orderShowDTO,
                                        @RequestParam(value = "status") StatusType statusType) {
        orderService.updateStatusByOrderId(orderShowDTO.getId(), statusType);
        return "redirect:/orders/show";
    }
}
