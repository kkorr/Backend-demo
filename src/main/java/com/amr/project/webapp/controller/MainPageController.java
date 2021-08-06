package com.amr.project.webapp.controller;


import com.amr.project.service.abstracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainPageController {
    private final MainPageItemService mainPageItemService;
    private final MainPageShopService mainPageShopService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public MainPageController(MainPageItemService mainPageItemService, MainPageShopService mainPageShopService,
                              UserService userService, CategoryService categoryService) {
        this.mainPageItemService = mainPageItemService;
        this.mainPageShopService = mainPageShopService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/home")
    public String getPopularItemsAbdShop(Model model /*,Principal principal*/) {
        //model.addAttribute("user", userService.findByUsername(principal.getName()));
        //model.addAttribute("items", mainPageItemService.findPopularItems());
        model.addAttribute("shops", mainPageShopService.findPopularShops());
        model.addAttribute("categories", categoryService.getCategoryDto());
        return "home";
    }
}
