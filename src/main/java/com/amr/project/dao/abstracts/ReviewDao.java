package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Review;

import java.util.List;

public interface ReviewDao extends ReadWriteDAO<Review, Long> {
    List<Review> getUnmoderatedReviews();
}
