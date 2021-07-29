package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */
@Service
public interface ShopService {
    Shop findShopById(Long id);

    Shop findShopByName(String name);

    void save(Shop shop);

    void delete(Shop shop);

    void update(Shop shop);

    List<Shop> findAll();
}
