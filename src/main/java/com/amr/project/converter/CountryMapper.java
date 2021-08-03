package com.amr.project.converter;

import com.amr.project.model.entity.Country;
import org.mapstruct.Mapper;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper
public interface CountryMapper {

    default String map(Country country) {
        return country != null ? country.getName() : "No country!";
    }
}
