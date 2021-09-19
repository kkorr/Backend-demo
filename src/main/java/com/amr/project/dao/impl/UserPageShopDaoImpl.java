package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserPageShopDao;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserPageShopDaoImpl extends ReadWriteDAOImpl<Shop, Long> implements UserPageShopDao {

    @Override
    public List<Shop> getShopsByUserId(Long userId) {
        return entityManager.createQuery("select s from Shop s where s.user.id = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }
}
