package com.amr.project.converter;

import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.CategoryService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring")
public abstract class ReferenceCategoryMapper {
    @Autowired
    private
    CategoryService categoryService;

    public Collection<Category> map(String[] categories) {
        return Arrays.stream(categories)
                .map(categoryService::getByName)
                .collect(Collectors.toList());
    }
}
