package by.itacademy.javaenterprise.seledtsova.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemForOrderDTO {
    private String name;
    private Long count;
    private BigDecimal price;
}
