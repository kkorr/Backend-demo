package com.amr.project.converter;

import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}