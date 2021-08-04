package com.amr.project.converter;

import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.CategoryService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ReferenceCategoryMapper {
    private final
    CategoryService categoryService;

    public ReferenceCategoryMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Collection<Category> map(String[] categories) {
        return Arrays.stream(categories)
                .map(categoryService::getByName)
                .collect(Collectors.toList());
    }
}
