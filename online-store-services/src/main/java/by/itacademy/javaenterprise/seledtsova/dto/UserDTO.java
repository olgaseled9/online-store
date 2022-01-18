package by.itacademy.javaenterprise.seledtsova.dto;

import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String lastName;
    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String patronymic;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private RoleType role;

}
