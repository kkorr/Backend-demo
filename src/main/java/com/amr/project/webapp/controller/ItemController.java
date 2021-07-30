package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Item;
import com.amr.project.service.impl.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ItemController {

    private ItemServiceImpl itemService;

    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }



    @GetMapping("/product/{id}") //product/1
    public String itemById(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.itemById(id));
        //в карточке товара вернуться параметры для Страницы товара:
//        item.getId();
//        item.getName();
//        item.getPrice();
//        item.getImages();
//        item.getCategories();
//        item.getDescription(); //описание товара текст
//        item.getCount();
//        item.getRating();
//        item.getShop();
        return "product_page";
    }
}
