package by.itacademy.javaenterprise.seledtsova.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ItemDTO {
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 200)
    private String description;

    private MultipartFile imageFile;
}
