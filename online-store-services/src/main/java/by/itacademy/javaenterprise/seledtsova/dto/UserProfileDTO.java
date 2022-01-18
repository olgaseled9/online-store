package by.itacademy.javaenterprise.seledtsova.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserProfileDTO {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String lastName;
    @NotNull
    @NotEmpty
    private String patronymic;
    @NotNull
    @Size(max = 40)
    private String email;
    private String telephone;
}
