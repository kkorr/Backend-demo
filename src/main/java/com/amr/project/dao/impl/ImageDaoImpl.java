package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ImageDao;
import com.amr.project.model.entity.Image;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl extends ReadWriteDAOImpl<Image, Long> implements ImageDao {
    @Override
    public Image getByUrl(String url) {
        return entityManager.createQuery("SELECT i from Image i where i.url = :url", Image.class)
                .setParameter("url", url).getResultList()
                .stream()
                .findFirst().orElse(new Image());
    }
}
