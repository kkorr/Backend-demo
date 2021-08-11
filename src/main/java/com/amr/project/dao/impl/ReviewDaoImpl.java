package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ReviewDao;
import com.amr.project.model.entity.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDaoImpl extends ReadWriteDAOImpl<Review, Long> implements ReviewDao {
    @Override
    public List<Review> getUnmoderatedReviews() {
        return entityManager.createQuery("SELECT r from Review r where r.isModerateAccept = false and r.isModerated = false", Review.class)
                .getResultList();
    }
}
