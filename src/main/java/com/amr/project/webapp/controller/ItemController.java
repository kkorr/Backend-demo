package com.amr.project.webapp.controller;

import com.amr.project.service.impl.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {

    private ItemServiceImpl itemService;

    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/product/{id}")
    public String itemById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", itemService.getByKey(id));
        return "product_page";
    }
}
