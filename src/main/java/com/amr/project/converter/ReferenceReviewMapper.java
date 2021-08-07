package com.amr.project.converter;

import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring")
public abstract class ReferenceReviewMapper {
    @Autowired
    protected ReviewService reviewService;

    public Collection<Review> map(String[] reviews) {
        if (reviews == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(reviews)
                .map(reviewService::getByName)
                .collect(Collectors.toList());
    }
}
