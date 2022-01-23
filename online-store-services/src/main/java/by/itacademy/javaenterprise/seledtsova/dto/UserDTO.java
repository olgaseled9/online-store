package by.itacademy.javaenterprise.seledtsova.dto;

import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    @NotNull(message = "firstname cannot be null")
    @NotEmpty
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[A-Za-z]*$", message = "Only the Latin alphabet must be used")
    private String firstName;
    @NotNull(message = "lastname cannot be null")
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Only the Latin alphabet must be used")
    @Size(min = 2, max = 40)
    private String lastName;
    @Pattern(regexp = "^[A-Za-z]*$", message = "Only the Latin alphabet must be used")
    @NotNull(message = "patronymic cannot be null")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String patronymic;
    @NotNull(message = "username cannot be null")
    @NotEmpty
    @Size(min = 2, max = 50)
    private String username;
    @NotNull(message = "password cannot be null")
    @NotEmpty
    @Size(min = 2, max = 50)
    private String password;
    private RoleType role;

}
