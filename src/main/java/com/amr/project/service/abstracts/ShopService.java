package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */


public interface ShopService extends ReadWriteService<Shop, Long>{
    Shop findShopById(Long id);

    Shop findShopByName(String name);
}
