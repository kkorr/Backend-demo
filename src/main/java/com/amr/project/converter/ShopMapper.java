package com.amr.project.converter;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper(uses = {ItemMapper.class, ReviewMapper.class, DiscountMapper.class, CountryMapper.class,
ImageMapper.class})
public interface ShopMapper {
    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "logo", target = "logo"),
            @Mapping(source = "location", target = "location"),
            @Mapping(source = "items", target = "items"),
            @Mapping(source = "reviews", target = "reviews"),
            @Mapping(source = "rating", target = "rating"),
            @Mapping(source = "discounts", target = "discounts"),
            @Mapping(source = "count", target = "count"),
            @Mapping(source = "user", target = "username"),
            @Mapping(source = "moderated", target = "moderated"),
            @Mapping(source = "moderateAccept", target = "moderateAccept"),
            @Mapping(source = "moderatedRejectReason", target = "moderatedRejectReason"),
            @Mapping(source = "pretendentToBeDeleted", target = "pretendentToBeDeleted")
    })
    ShopDto shopToShopDto(Shop shop);


    default Long map(Shop shop) {
        return shop.getId();
    }

    default String map(User user) {
        return user != null ? user.getUsername() : "No user!";
    }
}
