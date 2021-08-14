package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShowcaseCategoryDao;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ShowcaseCategoryDaoImpl extends ReadWriteDAOImpl<Category, Long> implements ShowcaseCategoryDao {
    /**
     * Метод для поиска категорий, которые присутствуют в магазине
     * @param shopId
     * @return
     */
    @Override
    public List<Category> findCategoryByShopId(Long shopId) {
        return entityManager.createNativeQuery("select distinct сategory.id, сategory.name from сategory join item_category join shop_item join item on сategory.id = item_category.category_id and item_category.item_id = shop_item.item_id and shop_item.shop_id = :shopId and item_category.item_id = item.id and item.is_moderated = true and item.is_moderate_accept = true;", Category.class)
                .setParameter("shopId", shopId)
                .getResultList();
    }
}
