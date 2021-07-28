package com.amr.project.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel
public class MainPageShopDto {
    private Long id;
    private String name;
    private float rating;
    private String imageLogo;
}
