package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Контроллер страницы всех магазинов, которые прошли модерацию
 */

@Controller
public class ShopsController {

    private ShopService shopService;

    ShopsController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shops")
    @ResponseBody
    public List<Shop> getAllModeratedShops() {
        return shopService.findModeratedShops();
    }
}
