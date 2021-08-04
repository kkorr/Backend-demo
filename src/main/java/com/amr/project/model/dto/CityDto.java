package com.amr.project.model.dto;

import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

//@Data
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiIgnore
public class CityDto {
    private Long id;
    private String name;
    private String[] countries;
}
