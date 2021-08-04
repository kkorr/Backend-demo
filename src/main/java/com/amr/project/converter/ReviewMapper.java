package com.amr.project.converter;

import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collection;
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

    default String[] map(Collection<Review> reviews) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        int iterCount = 0;
        String[] strings = new String[reviews.size()];
        for (Review review :
                reviews) {
            strings[iterCount] = review.getText();
        }
        return strings;
    }
}
