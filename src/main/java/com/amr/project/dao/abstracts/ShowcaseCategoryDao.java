package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Category;

import java.util.List;

public interface ShowcaseCategoryDao extends ReadWriteDAO<Category, Long> {
    List<Category> findCategoryByShopId(Long shopId);
}
