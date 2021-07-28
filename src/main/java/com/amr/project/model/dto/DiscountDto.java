package com.amr.project.model.dto;

import lombok.Data;

@Data
public class DiscountDto {
    private Long id;
    private int minOrder;
    private int percentage;
    private int fixedDiscount;
    private long shopId;
    private long userId;
}
