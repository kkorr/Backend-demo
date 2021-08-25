package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReviewDao;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl extends ReadWriteServiceImpl<Review, Long> implements ReviewService {
    private final ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao) {
        super(reviewDao);
        this.reviewDao = reviewDao;
    }

    @Override
    public List<Review> getUnmoderatedReviews() {
        return reviewDao.getUnmoderatedReviews();
    }
}
