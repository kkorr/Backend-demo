package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.MainPageItemsDao;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainPageItemsDaoImpl extends ReadWriteDAOImpl<Item, Long> implements MainPageItemsDao {

    /**
     * Этот метод используется для выборки Item
     * по id категории
     */
    @Override
    public List<Item> findItemsByCategoryId(Long categoryId) {
        return entityManager.createQuery("SELECT i FROM Item i JOIN i.categories c where c.id = :id", Item.class)
                .setParameter("id", categoryId).getResultList();
    }
}
