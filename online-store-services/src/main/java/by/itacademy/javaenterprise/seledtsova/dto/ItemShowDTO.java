package by.itacademy.javaenterprise.seledtsova.dto;

import by.itacademy.javaenterprise.seledtsova.entity.StatusType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ItemShowDTO {
    private String name;
    private Long orderId;
    private StatusType statusType;
    private Long count;
    private BigDecimal finalPrice;
    private LocalDate date;
}
