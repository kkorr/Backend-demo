package com.amr.project.converter;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper(uses = {ItemMapper.class, ReviewMapper.class, DiscountMapper.class, CountryMapper.class,
        ImageMapper.class, ReferenceShopMapper.class,
        ReferenceCountryMapper.class, ReferenceImageMapper.class})
public interface ShopMapper {

    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    ShopDto shopToShopDto(Shop shop);

    Shop shopDtoToShop(ShopDto shopDto);


    default Long map(Shop shop) {
        return shop.getId();
    }

    default String map(User user) {
        return user != null ? user.getUsername() : "No user!";
    }
}
