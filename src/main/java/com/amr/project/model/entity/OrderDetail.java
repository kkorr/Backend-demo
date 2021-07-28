package com.amr.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "price")
public class OrderDetail {

    private BigDecimal total;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String country;
    private String city;
    private String index;
    private String street;
    private String house;
    private String buyerName;
    private String buyerPhone;

}
