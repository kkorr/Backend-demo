package com.amr.project.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*контроллер страницы витрины магазина*/
@Controller
@RequestMapping("/showcase")
public class ShowcaseController {
    public ShowcaseController() {}

    /*Далее будет использоваться path variable*/
    @GetMapping
    public String getShowcaseView(Model model) {
        model.addAttribute("market_id", "1");
        model.addAttribute("market_name", "Ozon (test)");
        return "showcase";
    }

}
