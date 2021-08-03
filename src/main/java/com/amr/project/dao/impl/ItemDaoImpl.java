package com.amr.project.dao.impl;


import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl extends ReadWriteDAOImpl<Item, Long> implements ItemDao {
    @Override
    public List<Item> getItemsByShopId(Long shopId) {
        return entityManager.createQuery("SELECT Item FROM Item as i where i.shop = :shopId")
                .setParameter("shopId", shopId).getResultList();
    }
}
