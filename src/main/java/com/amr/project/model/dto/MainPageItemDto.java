package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MainPageItemDto {
    private Long id;
    private String name;
    private double price;
    private int discount;
    private float rating;
    private String imageUrl;
}
