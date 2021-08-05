package com.amr.project.converter;

import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    public CategoryDto categoryToDto(Category category);
    public Category dtoToCategory(CategoryDto categoryDto);

    default String[] map(Collection<Category> categories) {
        if (categories == null) {
            return new String[0];
        }
        String[] strings = new String[categories.size()];
        int iterCount = 0;
        for (Category category :
                categories) {
            strings[iterCount] = category.getName();
            iterCount++;
        }
        return strings;
    }


   List<CategoryDto> toCategoryDto(List<Category> categories);
}
