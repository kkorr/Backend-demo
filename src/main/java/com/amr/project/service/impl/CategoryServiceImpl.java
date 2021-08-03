package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.CategoryDao;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ReadWriteServiceImpl<Category, Long> implements CategoryService{

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(ReadWriteDAO<Category, Long> readWriteDAO, CategoryDao categoryDao) {
        super(readWriteDAO);
        this.categoryDao = categoryDao;
    }
}
