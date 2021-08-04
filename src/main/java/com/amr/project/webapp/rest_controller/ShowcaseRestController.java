package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
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
    private ShopService shopService;
    @Autowired
    public ShowcaseRestController(ItemService itemService, ShopService shopService) {
        this.shopService = shopService;
        this.itemService = itemService;
    }
    public ShowcaseRestController() {}

    @GetMapping("/popular")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> getPopularItems(@PathVariable("id") Long marketId) {
        return new ResponseEntity<>(itemService.getPopularItemsByShopId(marketId), HttpStatus.OK);
    }
    @GetMapping("/items")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> getItems(@PathVariable("id") Long marketId) {
        return new ResponseEntity<>(itemService.getItemsByShopId(marketId), HttpStatus.OK);
    }
    @GetMapping("/description")
    @ResponseBody
    public ResponseEntity<ShopDto> getDescription(@PathVariable("id") Long marketId) {
        return new ResponseEntity<>(ShopMapper.INSTANCE.shopToShopDto(shopService.getByKey(marketId)), HttpStatus.OK);
    }

}
