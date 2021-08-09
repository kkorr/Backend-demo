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
        return entityManager.createQuery("SELECT i FROM Item i JOIN i.shop s on s.id = :shopId", Item.class)
                .setParameter("shopId", shopId)
                .getResultList()
                .stream()
                .map(i -> i.getCategories())
                .flatMap(cs -> cs.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
