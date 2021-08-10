package com.amr.project.service.impl;

import com.amr.project.converter.CategoryMapper;
import com.amr.project.dao.abstracts.ShowcaseCategoryDao;
import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.ShowcaseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShowcaseCategoryServiceImpl extends ReadWriteServiceImpl<Category, Long> implements ShowcaseCategoryService {

    private ShowcaseCategoryDao showcaseCategoryDao;
    private CategoryMapper categoryMapper;

    @Autowired
    public ShowcaseCategoryServiceImpl(ShowcaseCategoryDao showcaseCategoryDao,
                                       CategoryMapper categoryMapper) {
        super(showcaseCategoryDao);
        this.showcaseCategoryDao = showcaseCategoryDao;
        this.categoryMapper = categoryMapper;
    }
    @Override
    public List<CategoryDto> findCategoryByShopId(Long shopId) {
        return categoryMapper.toCategoryDto(showcaseCategoryDao.findCategoryByShopId(shopId));
    }
}
