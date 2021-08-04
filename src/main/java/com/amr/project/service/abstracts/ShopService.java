package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Shop;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */


public interface ShopService extends ReadWriteService<Shop, Long> {
    Shop findShopById(Long id);

    Shop findShopByName(String name);
}
