package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;

import java.util.List;

public interface ShowcaseItemService extends ReadWriteService<Item, Long> {
    List<ItemDto> findPopularItemsByShopId(Long shopId);
    List<ItemDto> findItemsByShopId(Long shopId);
    List<ItemDto> findItemsByCategoryIdAndShopId(Long categoryId, Long shopId);
    List<ItemDto> searchItemsByName(Long shopId, String itemName);
}
