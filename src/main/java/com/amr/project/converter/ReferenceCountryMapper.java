package com.amr.project.converter;

import com.amr.project.model.entity.Country;
import com.amr.project.service.abstracts.CountryService;
import org.springframework.stereotype.Component;


@Component
public class ReferenceCountryMapper {
    private final CountryService countryService;

    public ReferenceCountryMapper(CountryService countryService) {
        this.countryService = countryService;
    }

    public Country map(String name) {
        return countryService.getByName(name);
    }
}
