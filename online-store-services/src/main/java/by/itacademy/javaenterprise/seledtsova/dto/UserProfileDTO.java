package by.itacademy.javaenterprise.seledtsova.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserProfileDTO {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min=2, max = 20)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(min=2, max = 30)
    private String lastName;
    @NotNull
    @NotEmpty
    @Size(min=2)
    private String patronymic;
    @NotNull
    @Size(max = 40)
    private String email;
    private String telephone;
}
