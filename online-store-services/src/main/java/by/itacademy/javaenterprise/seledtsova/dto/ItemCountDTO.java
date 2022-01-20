package by.itacademy.javaenterprise.seledtsova.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ItemCountDTO {
    private Long itemId;
    @NotNull
    @Min(1)
    @Max(15)
    private Integer itemCount;
}
