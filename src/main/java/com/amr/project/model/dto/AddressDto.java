package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
    private Long id;
    private String cityIndex;
    private String country;
    private String city;
    private String street;
    private String house;
}
