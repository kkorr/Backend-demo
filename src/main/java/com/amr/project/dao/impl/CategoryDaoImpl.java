package com.amr.project.dao.impl;


import com.amr.project.dao.abstracts.CategoryDao;
import com.amr.project.model.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends ReadWriteDAOImpl<Category, Long> implements CategoryDao {
    @Override
    public Category getCategoryByName(String name) {
        return entityManager.createQuery("SELECT c from Category c where c.name = :name", Category.class)
                .setParameter("name", name).getResultList()
                .stream()
                .findFirst().orElse(new Category());
    }
}
