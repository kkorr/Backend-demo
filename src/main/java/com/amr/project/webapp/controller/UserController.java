package com.amr.project.webapp.controller;

import com.amr.project.service.abstracts.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/registration")
    public String registerUser() {
        return "registration";
    }


    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = emailService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "Вы были успешно активированы.");
        } else {
            model.addAttribute("message", "Код активации не найден");
        }
        return "activate";
    }
}