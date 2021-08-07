package com.amr.project.converter;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShopsMapper {
    ShopsMapper INSTANCE = Mappers.getMapper(ShopsMapper.class);

    @Mapping(source = "shop.user.username", target = "username")
    ShopDto toShopDto(Shop shop);

    default String getLogo(Image logo) {
        return logo.getUrl();
    }

    default String getLocation(Country location) {
        return location.getName();
    }
}
