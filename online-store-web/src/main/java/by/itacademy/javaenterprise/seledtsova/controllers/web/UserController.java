package by.itacademy.javaenterprise.seledtsova.controllers.web;

import by.itacademy.javaenterprise.seledtsova.dto.UserDTO;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import by.itacademy.javaenterprise.seledtsova.exception.UserAlreadyExistException;
import by.itacademy.javaenterprise.seledtsova.service.RoleService;
import by.itacademy.javaenterprise.seledtsova.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping("/allusers")
    public String getAllUsers(Model model) {
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("users", users);
        return "all_users";
    }

    @GetMapping("/add")
    public String addUserPage(UserDTO userDTO, Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        model.addAttribute("roles", roleService.findAll());
        if (!bindingResult.hasErrors()) {
            userService.addUser(userDTO);
            log.info("New user add successfully");
            return "redirect:/users/allusers";
        } else {
            log.error("New user not added");
            return "add";
        }
    }

    @GetMapping("/remove")
    public String removeUserById(@RequestParam("id") Long id) {
        if (id != null) {
            userService.removeUserById(id);
            log.info("User removed successfully");
        }
        return "redirect:/users/allusers";
    }

    @GetMapping("/update-role")
    public String getUpdateUserRolePage(@RequestParam Long id, Model model, UserDTO userDTO) {
        model.addAttribute("roles", roleService.findAll());
        return "update_user";
    }

    @PostMapping("/update-role")
    public String updateUserRoleById(UserDTO userDTO,
                                     @RequestParam(value = "role") RoleType roleType) {
        userService.updateUserRoleById(userDTO.getId(), roleType);
        log.info("User's role update successfully");
        return "redirect:/users/allusers";
    }
}
