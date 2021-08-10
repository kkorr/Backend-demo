package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.entity.Category;

import java.util.List;

public interface ShowcaseCategoryService extends ReadWriteService<Category, Long> {
    List<CategoryDto> findCategoryByShopId(Long shopId);
}
