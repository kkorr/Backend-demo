package com.amr.project.converter;

import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper
public interface ReviewMapper {
    default List<Review> map(Shop shop) {
        return shop.getReviews();
    }
}
