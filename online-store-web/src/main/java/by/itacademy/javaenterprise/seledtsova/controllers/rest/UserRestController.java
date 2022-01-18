package by.itacademy.javaenterprise.seledtsova.controllers.rest;

import by.itacademy.javaenterprise.seledtsova.dto.UserDTO;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import by.itacademy.javaenterprise.seledtsova.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;


    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getUsers();
    }


    @PostMapping("/users")
    public ResponseEntity<Void> addNewUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            userDTO.setRole(RoleType.ROLE_ADMINISTRATOR);
            userService.addUser(userDTO);
            log.debug("Added user with username {}", userDTO.getUsername());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.removeUserById(id);
        log.info("Removed user with id {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUserRoleById(@PathVariable Long id, @RequestBody UserDTO user) {
        Optional<UserDTO> userDTOOptional = userService.getUsers().stream()
                .filter(userDTO -> userDTO.getId().equals(id))
                .findFirst();
        if (userDTOOptional.isPresent()) {
            UserDTO userDTO = userDTOOptional.get();
            userDTO.setRole(user.getRole());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
