package com.amr.project.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscountController {

    @GetMapping("/discounts")
    public String discountPage() {
        return "discounts";
    }
}
