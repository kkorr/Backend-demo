package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShowcaseItemDao;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShowcaseItemDaoImpl extends ReadWriteDAOImpl<Item, Long> implements ShowcaseItemDao {
    /**
     * Метод используется для выборки итемов по id магазина
     * @param shopId
     * @return
     */
    @Override
    public List<Item> findItemsByShopId(Long shopId) {
        return entityManager.createQuery("SELECT i FROM Item i JOIN i.shop s on s.id = :id WHERE i.isModerateAccept = true AND i.isModerated = true", Item.class)
                .setParameter("id", shopId)
                .getResultList();
    }

    /**
     * Метод используется для выборки итемов по id категории и id магазина
     * @param categoryId
     * @return
     */
    @Override
    public List<Item> findItemsByCategoryIdAndShopId(Long categoryId, Long shopId) {
        return entityManager.createQuery("SELECT i FROM Item i JOIN i.categories c on c.id = :categoryId JOIN i.shop s on s.id = :shopId WHERE i.isModerateAccept = true AND i.isModerated = true", Item.class)
                .setParameter("categoryId", categoryId)
                .setParameter("shopId", shopId)
                .getResultList();
    }

    /**
     * Метод используется для выборки итема по части наименования и shop id
     * @param shopId
     * @param itemName
     * @return
     */
    @Override
    public List<Item> searchItemsByName(Long shopId, String itemName) {
        return entityManager.createQuery("SELECT i FROM Item i JOIN i.shop s on s.id = :shopId WHERE i.isModerateAccept = true AND i.isModerated = true AND i.name LIKE :itemName", Item.class)
                .setParameter("shopId", shopId)
                .setParameter("itemName", "%" + itemName + "%")
                .getResultList();
    }

    /**
     * Ищет популярные товары по id магазина
     * @param shopId
     * @return
     */
    @Override
    public List<Item> findPopularItemsBYShopId(Long shopId) {
        return entityManager.createQuery("SELECT i FROM Item i JOIN i.shop s on s.id = :shopId WHERE i.isModerateAccept = true AND i.isModerated = true ORDER BY i.count DESC", Item.class)
                .setMaxResults(5)
                .setParameter("shopId", shopId).getResultList();
    }
}
