package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDaoImpl extends ReadWriteDAOImpl<Shop, Long> implements ShopDao {

    @Override
    public Shop findShopByName(String name) {
        return entityManager.createQuery("SELECT s from Shop s where s.name = :name", Shop.class)
                .setParameter("name", name).getResultList()
                .stream()
                .findFirst().orElse(new Shop());
    }

    @Override
    public Shop findShopById(Long id) {
        return entityManager.createQuery("SELECT s from Shop s where s.id = :id", Shop.class)
                .setParameter("id", id).getResultList()
                .stream()
                .findFirst().orElse(new Shop());
    }
}
