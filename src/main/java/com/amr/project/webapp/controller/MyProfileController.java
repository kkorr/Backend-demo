package com.amr.project.webapp.controller;

import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/myprofile")
public class MyProfileController {

    private final UserService userService;

    public MyProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getProfile(Model model) {
        model.addAttribute("user", userService.getUser());
        return "myProfile";
    }

    @PatchMapping("/edit")
    public String editProfile(@ModelAttribute User user) {
        userService.editUser(user);
        return "redirect:/myprofile";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProfile(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/myprofile";
    }
}
