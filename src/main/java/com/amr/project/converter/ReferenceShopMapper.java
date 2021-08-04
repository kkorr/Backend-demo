package com.amr.project.converter;

import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.CountryService;
import com.amr.project.service.abstracts.ImageService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Component;

@Component
public class ReferenceShopMapper {
    private final
    ShopService shopService;

    public ReferenceShopMapper(ShopService shopService, ImageService imageService, CountryService countryService, UserService userService) {
        this.shopService = shopService;
    }

    public Shop map(Long id) {
        return shopService.findShopById(id);
    }


}
