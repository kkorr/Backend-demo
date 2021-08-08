package com.amr.project.converter;

import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper(uses = {ReferenceReviewMapper.class,
        ItemMapper.class,
        ShopMapper.class,
        UserMapper.class}, componentModel = "spring")
public interface ReviewMapper {

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.firstName", target = "userFirstName"),
            @Mapping(source = "user.lastName", target = "userLastName"),
            @Mapping(source = "user.email", target = "user_email"),
            @Mapping(source = "item.id", target = "itemId"),
            @Mapping(source = "item.name", target = "itemName"),
            @Mapping(source = "shop.id", target = "shopId"),
            @Mapping(source = "shop.name", target = "shopName")
    })
    ReviewDto reviewToReviewDto(Review review);
    @Mappings({
            @Mapping(source = "userId", target = "user.id"),
            @Mapping(source = "itemId", target = "item.id"),
            @Mapping(source = "shopId", target = "shop.id"),
    })
    Review reviewDtoToReview(ReviewDto reviewDto);

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
