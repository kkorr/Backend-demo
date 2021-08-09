package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;

public interface ShowcaseShopService extends ReadWriteService<Shop, Long> {
    ShopDto getDtoByKey(Long id);
}
