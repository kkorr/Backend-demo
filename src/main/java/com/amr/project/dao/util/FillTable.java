package com.amr.project.dao.util;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */

@Component
public class FillTable {
    private final ShopService shopService;
    private final ItemService itemService;

    public FillTable(ShopService shopService, ItemService itemService) {
        this.shopService = shopService;
        this.itemService = itemService;
    }

    @PostConstruct
    public void fillTable() {
        Item item = new Item("testItem", new BigDecimal(20), 2, 10d, "1");
        Item item2 = new Item("testItem2", new BigDecimal(20), 2, 10d, "2");
        Item item3 = new Item("testItem3", new BigDecimal(20), 2, 10d, "3");
        itemService.save(item);
        itemService.save(item2);
        itemService.save(item3);
        Shop shop = new Shop("testShop", "testShop@mail.ru", "+79999999999", "testShop");
        Shop shop2 = new Shop("testShop2", "testShop2@mail.ru", "+79999999999", "testShop2");
        item.setShop(shop);
        item2.setShop(shop);
        item3.setShop(shop2);
        shopService.save(shop);
        shopService.save(shop2);
        shop.setItems(new ArrayList<>(List.of(item, item2)));
        shopService.save(shop);
    }
}
