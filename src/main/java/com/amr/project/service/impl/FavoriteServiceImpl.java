package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.dao.abstracts.FavoriteDao;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Favorite;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteServiceImpl extends ReadWriteServiceImpl<Favorite, Long> implements FavoriteService {

    private final FavoriteDao favoriteDao;

    @Autowired
    public FavoriteServiceImpl(ReadWriteDAO<Favorite, Long> readWriteDAO, FavoriteDao favoriteDao) {
        super(readWriteDAO);
        this.favoriteDao = favoriteDao;
    }

    public Optional<Favorite> findByUser(User user) {
        return favoriteDao.findByUser(user);
    }
}
