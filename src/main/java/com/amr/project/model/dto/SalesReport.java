package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class SalesReport {

    private String name;
    private BigDecimal price;
    private Integer count;
    private BigDecimal total;
    private String date;

}
