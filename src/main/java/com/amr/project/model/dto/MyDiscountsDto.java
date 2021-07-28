package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyDiscountsDto {
    private String name;
    private String url;
    private String logoUrl;
    private String description;
}
