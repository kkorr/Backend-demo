package com.amr.project.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopsController {

    @RequestMapping("/shops")
    public String cartAllShops() {
        return "shops";
    }
}
