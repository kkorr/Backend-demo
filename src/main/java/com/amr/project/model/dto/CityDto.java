package com.amr.project.model.dto;

import com.amr.project.model.entity.Address;
import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

//@Data
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiIgnore
public class CityDto {
    private Long id;
    private String name;
    private String country;
    private List<Address> addresses;
}
