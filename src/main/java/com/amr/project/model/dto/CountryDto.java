package com.amr.project.model.dto;

import com.amr.project.model.entity.City;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
public class CountryDto {
    private Long id;
    private String name;
    private Collection<City> cities;
}
