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
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    List<CategoryDto> toCategoryDto(List<Category> categories);
}
