package com.amr.project.service.abstracts;


import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopService extends ReadWriteService<Shop, Long> {
    Shop findShopById(Long id);

    Shop findShopByName(String name);

    List<Shop> getUnmoderatedShops();

    List<Shop> findModeratedShops();
}
