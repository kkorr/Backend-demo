package com.amr.project.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("")
public class LoginController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());;

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }
//TODO логирование авторизованного пользователя
    @PostMapping(value = "/login")
    public void getUsername(@ModelAttribute("j_username") String username) {
        LOGGER.info("Login this" + username);
    }
}
