package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReviewDao;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewService;

public class ReviewServiceImpl extends ReadWriteServiceImpl<Review, Long> implements ReviewService {
    private final ReviewDao reviewDao;

    public ReviewServiceImpl(ReviewDao reviewDao) {
        super(reviewDao);
        this.reviewDao = reviewDao;
    }
}
