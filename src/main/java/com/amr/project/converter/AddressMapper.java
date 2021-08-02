package com.amr.project.converter;

import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.entity.Address;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    @Mapping(source = "country.name", target = "country")
    @Mapping(source = "city.name", target = "city")
    AddressDto addressToDto(Address address);

    @Mapping(source = "country", target = "country.name")
    @Mapping(source = "city", target = "city.name")
    Address dtoToAddress(AddressDto addressDto);
}
