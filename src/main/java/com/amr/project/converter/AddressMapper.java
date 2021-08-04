package com.amr.project.converter;

import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.entity.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);


    @Mappings({
            @Mapping(source = "country.name", target = "country"),
            @Mapping(source = "city.name", target = "city")
    })
    public AddressDto addressToDto(Address address);

   // @InheritInverseConfiguration
    //public Address dtoToAddress(AddressDto addressDto);


}
