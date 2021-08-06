package com.amr.project.converter;

import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ReferenceCategoryMapper {
    @Autowired
    protected CategoryService categoryService;

    public Collection<Category> map(String[] categories) {
        if (categories == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(categories)
                .map(categoryService::getCategoryByName)
                .collect(Collectors.toList());
    }
}
