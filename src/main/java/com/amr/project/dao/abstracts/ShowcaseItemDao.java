package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;

import java.util.List;

public interface ShowcaseItemDao extends ReadWriteDAO<Item, Long> {
    List<Item> findItemsByShopId(Long shopId);
    List<Item> findItemsByCategoryIdAndShopId(Long categoryId, Long shopId);
    List<Item> searchItemsByName(Long shopId, String itemName);
    List<Item> findPopularItemsBYShopId(Long shopId);
}
