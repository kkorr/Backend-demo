package com.amr.project.converter;

import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring")
public abstract class ReferenceShopMapper {
    @Autowired
    protected ShopService shopService;

    public Shop map(Long shopId) {
        return shopService.getByKey(shopId);
    }
}
