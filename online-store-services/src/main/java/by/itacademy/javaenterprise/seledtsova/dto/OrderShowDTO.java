package by.itacademy.javaenterprise.seledtsova.dto;

import by.itacademy.javaenterprise.seledtsova.entity.StatusType;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderShowDTO {
    private Long id;
    private StatusType statusType;
    @ToString.Exclude
    private List<ItemForOrderDTO> items = new ArrayList<>();
    private Long itemsCount;
    private BigDecimal finalPrice;
    private String firstName;
    private String lastName;
    private String telephone;
}
