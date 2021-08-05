package com.amr.project.converter;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CategoryMapper.class, ImageMapper.class, ShopMapper.class, CountryMapper.class, ReviewMapper.class,
        UserMapper.class, ReferenceCategoryMapper.class, ReferenceImageMapper.class, ReferenceReviewMapper.class},
        componentModel = "spring")
public interface CartItemMapper {

    CartItemDto cartItemToDto(CartItem cartItem);

    CartItem dtoToCartItem(CartItemDto cartItemDto);
}
