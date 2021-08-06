package com.amr.project.converter;

import com.amr.project.model.dto.RoleDto;
import com.amr.project.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {


    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    public RoleDto roleToDto(Role role);

    public Role dtoToRole(RoleDto roleDto);


}
