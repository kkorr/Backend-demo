package com.amr.project.service.abstracts;


import com.amr.project.model.entity.Item;

import java.util.List;

public interface ItemService extends ReadWriteService<Item, Long>{
    Item findItemById(Long id);

    Item findItemByName(String name);
    List<Item> getItemsByShopId(Long shopId);
    List<Item> getPopularItemsByShopId(Long shopId);

}
