package com.amr.project.webapp.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class CartController {
    @GetMapping("/cart")
    public String registerUser(@CookieValue String cookie) {
        return "cart";
    }
}
}
