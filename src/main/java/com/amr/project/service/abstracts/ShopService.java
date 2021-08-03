package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopService extends ReadWriteService<Shop, Long> {
    List<ItemDto> getPopularItemsByMarketId(Long id);
    List<ItemDto> getItemsByMarketId(Long id);
    String getDescriptionByMarketId(Long id);
}
