package com.amr.project.converter;

import com.amr.project.model.entity.Country;
import com.amr.project.service.abstracts.CountryService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring")
public abstract class ReferenceCountryMapper {
    @Autowired
    private CountryService countryService;


    public Country map(String name) {
        return countryService.getByName(name);
    }
}
