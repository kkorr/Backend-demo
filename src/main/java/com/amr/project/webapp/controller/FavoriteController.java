package com.amr.project.webapp.controller;

import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class FavoriteController {

    private final UserService userService;

    @Autowired
    public FavoriteController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/favorites")
    public String getProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsername(authentication.getName());
        if (authentication.isAuthenticated() && user.isPresent()) {
            model.addAttribute("user", user.get());
        } else {
            model.addAttribute("user", new User());
        }
        return "favorites";
    }
}