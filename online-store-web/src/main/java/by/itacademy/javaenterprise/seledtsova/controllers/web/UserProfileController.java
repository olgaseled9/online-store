package by.itacademy.javaenterprise.seledtsova.controllers.web;

import by.itacademy.javaenterprise.seledtsova.dto.UserProfileDTO;
import by.itacademy.javaenterprise.seledtsova.service.UserProfileService;
import by.itacademy.javaenterprise.seledtsova.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class UserProfileController {

    @Autowired
    private final UserProfileService userProfileService;
    @Autowired
    private final UserService userService;

    @GetMapping("/get")
    public String getUserProfile(Principal principal, Model model) {
        UserProfileDTO profile = userProfileService.findUserProfileByUsername(principal.getName());
        model.addAttribute("profile", profile);
        return "get_user_profile";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserProfilePage(@PathVariable Long id, Model model, UserProfileDTO userProfileDTO) {
        userProfileDTO = userProfileService.getById(id);
        model.addAttribute("userProfileDTO", userProfileDTO);
        return "update_profile";
    }

    @PostMapping("/update")
    public String updateUserProfile(@Valid UserProfileDTO userProfileDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userProfileService.updateUserProfile(userProfileDTO);
            return "redirect:/profiles/get";
        }
        return "update_profile";
    }
}
