package com.amr.project.webapp.rest_controller;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*Rest контроллер для работы с товарами и описанием магазина*/
@RestController
@RequestMapping("/showcase/{id}")
public class ShowcaseRestController {

    private ItemService itemService;

    @Autowired
    public ShowcaseRestController(ItemService itemService) {
        this.itemService = itemService;
    }
    public ShowcaseRestController() {}

    @GetMapping("/popular")
    @ResponseBody
    public ResponseEntity<List<Item>> getPopularItems(@PathVariable("id") Long marketId) {
        return new ResponseEntity<>(itemService.getPopularItemsByShopId(marketId), HttpStatus.OK);
    }
    @GetMapping("/items")
    @ResponseBody
    public ResponseEntity<List<Item>> getItems(@PathVariable("id") Long marketId) {

        return new ResponseEntity<>(itemService.getItemsByShopId(marketId), HttpStatus.OK);
    }

}
