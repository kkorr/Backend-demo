package com.amr.project.webapp.controller;

import com.amr.project.service.abstracts.MainPageItemService;
import com.amr.project.service.abstracts.MainPageShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    private final MainPageItemService mainPageItemService;
    private final MainPageShopService mainPageShopService;

    @Autowired
    public MainPageController(MainPageItemService mainPageItemService, MainPageShopService mainPageShopService) {
        this.mainPageItemService = mainPageItemService;
        this.mainPageShopService = mainPageShopService;
    }
    @GetMapping(value = "?")
    public String getPopularItemsAbdShop(Model model){
        model.addAttribute("items",mainPageItemService.findPopularItems());
        model.addAttribute("shops",mainPageShopService.findPopularShops());
        return "?";
    }
}
