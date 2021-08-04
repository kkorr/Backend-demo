package com.amr.project.converter;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {ItemMapper.class, ImageMapper.class, CountryMapper.class
        })
public interface ShopMapper {

    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    @Mappings({
            @Mapping(source = "location.name", target = "location"),
            @Mapping(source = "user.username", target = "username")
    })
    public ShopDto shopToDto(Shop shop);
    //public Shop dtoToShop(ShopDto shopDto);

    default Long map(Shop shop) {
        return shop.getId();
    }

    default String map(User user) {
        return user != null ? user.getUsername() : "No user!";
    }
}
