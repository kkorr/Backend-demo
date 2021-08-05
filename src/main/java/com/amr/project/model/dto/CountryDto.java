package com.amr.project.model.dto;

import com.amr.project.model.entity.City;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@ApiIgnore
@Data
public class CountryDto {
    private Long id;
    private String name;
    private Collection<City> cities;
}
