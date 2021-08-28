package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDaoImpl extends ReadWriteDAOImpl<Shop, Long> implements ShopDao {
    @Override
    public Shop findShopByName(String name) {
        return null;
    }

    @Override
    public List<Shop> findUnmoderatedShops() {
        return entityManager.createQuery("SELECT s from Shop s where s.isModerateAccept = false and s.isModerated = false", Shop.class)
                .getResultList();
    }

    @Override
    public List<Shop> findModeratedShops() {
        return entityManager.createQuery("SELECT s from Shop s where s.isModerateAccept = true and s.isModerated = true", Shop.class)
                .getResultList();
    }
}

