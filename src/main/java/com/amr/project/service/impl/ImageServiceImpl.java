package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.ImageDao;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.model.entity.Image;
import com.amr.project.service.abstracts.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl extends ReadWriteServiceImpl<Image, Long> implements ImageService {

    private final ImageDao imageDao;

    @Autowired
    public ImageServiceImpl(ReadWriteDAO<Image, Long> readWriteDAO, ImageDao imageDao) {
        super(readWriteDAO);
        this.imageDao = imageDao;
    }

    @Override
    public Image getByUrl(String url) {
        return imageDao.getByUrl(url);
    }
}
