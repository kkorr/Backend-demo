package com.amr.project.webapp.controller;

import com.amr.project.converter.UserConverter;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/myprofile")
public class MyProfileController {

    private final UserService userService;
    private final UserConverter userConverter;

    public MyProfileController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/{id}")
    public String getProfile(@PathVariable("id") long id, Model model) {
        UserDto userDto = userConverter.toDto(userService.getUser(id));
        model.addAttribute("user", userDto);
        return "myProfile";
    }

    @PatchMapping("/edit")
    public String editProfile(@ModelAttribute UserDto userDto) {
        User user = userConverter.toEntity(userDto);
        userService.editUser(user);
        return "redirect:/myprofile";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProfile(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/myprofile";
    }
}
