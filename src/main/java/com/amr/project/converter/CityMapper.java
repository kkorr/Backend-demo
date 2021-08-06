package com.amr.project.converter;

import com.amr.project.model.dto.CityDto;
import com.amr.project.model.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CountryMapper.class})
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mappings({
            @Mapping(source = "country.name", target = "country")
    })
    public CityDto cityToDto(City city);

    @Mappings({
            @Mapping(source = "country", target = "country.name")
    })
    public City dtoToCity(CityDto cityDto);
}
