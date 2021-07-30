package com.amr.project.converter;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShopsMapper {
    ShopsMapper INSTANCE = Mappers.getMapper(ShopsMapper.class);
    ShopDto toShopDto(Shop shop);
}
