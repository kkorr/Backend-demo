package com.amr.project.webapp.controller;

import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*контроллер страницы витрины магазина*/
@Controller
@RequestMapping("/showcase")
public class ShowcaseController {

    private ShopService shopService;

    @Autowired
    public ShowcaseController(ShopService shopService) {
        this.shopService = shopService;
    }
    public ShowcaseController() {}


    @GetMapping("/{id}")
    public String getShowcaseView(Model model, @PathVariable Long id) {
        /*Наполнение статическим контентом (sidebar)*/
        model.addAttribute("market_id", id);
        model.addAttribute("market_name", shopService.getByKey(id).getName());
        return "showcase";
    }

}
