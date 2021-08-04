package com.amr.project.converter;

import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;

import java.util.Collection;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper
public interface DiscountMapper {
    default Collection<Discount> map(Shop shop) {
        return shop.getDiscounts();
    }
}
