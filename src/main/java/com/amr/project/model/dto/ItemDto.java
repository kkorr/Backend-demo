package com.amr.project.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;

/**
 * Поле shopId должно быть заполнено на фронте
 */

@NoArgsConstructor
@Getter
@Setter
@ApiIgnore
public class ItemDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private int count;

    private double rating;

    private String[] categories;

    private String[] images;

    private String description;

    private int discount;

    private boolean isModerated;

    private boolean isModerateAccept;

    private String moderatedRejectReason;

    private Long shopId;
    private boolean isPretendentToBeDeleted;
}
