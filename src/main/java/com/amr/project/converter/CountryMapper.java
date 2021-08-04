package com.amr.project.converter;

import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;

@Mapper
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);


    public CountryDto countryToDto(Country country);

    public Country dtoToCountry(CountryDto countryDto);

    default String[] map(Collection<Country> countries) {
        if (countries == null) {
            countries = new ArrayList<>();
        }
        String[] strings = new String[countries.size()];
        int iterCount = 0;
        for (Country country:
                countries) {
            strings[iterCount] = country.getName();
            iterCount++;
        }
        return strings;
    }

    default String map(Country country) {
        return country != null ? country.getName() : "No country!";
    }

}
