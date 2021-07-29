package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */
@RestController
@RequestMapping("/seller/api")
public class SellerRestController {

    private static boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    private final ShopService shopService;
    private final ItemService itemService;

    public SellerRestController(ShopService shopService, ItemService itemService) {
        this.shopService = shopService;
        this.itemService = itemService;
    }

    @GetMapping("/{shopIdOrName}/settings")
    public ResponseEntity<ShopDto> getShop(@PathVariable("shopIdOrName") String shopIdOrName) {
        if (isNumeric(shopIdOrName)) {
            return new ResponseEntity<>(ShopMapper.INSTANCE.shopToShopDto(shopService.findShopById(Long.parseLong(shopIdOrName))), HttpStatus.OK);
        }
        return new ResponseEntity<>(ShopMapper.INSTANCE.shopToShopDto(shopService.findShopByName(shopIdOrName)), HttpStatus.OK);
    }

    @PatchMapping(value = "/{shopIdOrName}/settings")
    public ResponseEntity<?> updateShop(@RequestBody Shop shop) {
        shopService.update(shop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{shopIdOrName}/product/{productIdOrName}/newProduct")
    public ResponseEntity<?> addProduct(@RequestBody Item item, @PathVariable String productIdOrName, @PathVariable String shopIdOrName) {
        Shop shop = isNumeric(shopIdOrName) ? shopService.findShopById(Long.parseLong(shopIdOrName)) : shopService.findShopByName(shopIdOrName);
        List<Item> items = shop.getItems();
        item.setShop(shop);
        if (items == null) {
            items = new ArrayList<>();
        }
        itemService.save(item);
        items.add(item);
        shop.setItems(items);
        shopService.save(shop);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{shopIdOrName}/product/{productIdOrName}/edit")
    public ResponseEntity<ItemDto> getProduct(@PathVariable String productIdOrName, @PathVariable String shopIdOrName) {
        Shop shop = isNumeric(shopIdOrName) ? shopService.findShopById(Long.parseLong(shopIdOrName)) : shopService.findShopByName(shopIdOrName);
        Item item = isNumeric(productIdOrName) ? itemService.findItemById(Long.parseLong(productIdOrName)) : itemService.findItemByName(productIdOrName);
        boolean isShopItem = shop.getItems().contains(item);
        return isShopItem ? new ResponseEntity<>(ItemMapper.INSTANCE.itemToItemDto(item), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/{shopIdOrName}/product/{productIdOrName}/edit")
    public ResponseEntity<?> updateProduct(@RequestBody Item itemBody, @PathVariable String productIdOrName, @PathVariable String shopIdOrName) {
        Shop shop = isNumeric(shopIdOrName) ? shopService.findShopById(Long.parseLong(shopIdOrName)) : shopService.findShopByName(shopIdOrName);
        Item item = isNumeric(productIdOrName) ? itemService.findItemById(Long.parseLong(productIdOrName)) : itemService.findItemByName(productIdOrName);
        item = itemBody;
        item.setShop(shop);
        itemService.update(item);
        shopService.save(shop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{shopIdOrName}/product/{productIdOrName}/edit")
    public ResponseEntity<?> deleteProduct(@PathVariable String shopIdOrName, @PathVariable String productIdOrName) {
        Shop shop = isNumeric(shopIdOrName) ? shopService.findShopById(Long.parseLong(shopIdOrName)) : shopService.findShopByName(shopIdOrName);
        Item item = isNumeric(productIdOrName) ? itemService.findItemById(Long.parseLong(productIdOrName)) : itemService.findItemByName(productIdOrName);
        boolean isShopItem = shop.getItems().contains(item);
        if (isShopItem) {
            item.setPretendentToBeDeleted(true);
            shopService.save(shop);
        }
        return isShopItem ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
