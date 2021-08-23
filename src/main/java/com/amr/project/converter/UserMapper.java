package com.amr.project.converter;

import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ReadWriteService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AddressMapper.class, RoleMapper.class, DiscountMapper.class, CategoryMapper.class,
ImageMapper.class, ReadWriteService.class, CartItemMapper.class}, componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "address", target = "address")
    UserDto userToDto(User user);

    @Mapping(source = "address", target = "address")
    User dtoToUser(UserDto userDto);
}