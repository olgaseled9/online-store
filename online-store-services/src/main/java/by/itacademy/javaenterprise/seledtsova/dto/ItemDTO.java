package by.itacademy.javaenterprise.seledtsova.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ItemDTO {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String name;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotNull
    @NotEmpty
    @Size(max = 200)
    private String description;
}

