package com.amr.project.converter;

import com.amr.project.model.entity.Category;
import org.mapstruct.Mapper;

import java.util.Collection;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper
public interface CategoryMapper {
    default String[] map(Collection<Category> categories) {
        String[] strings = new String[categories.size()];
        int iterCount = 0;
        for (Category category :
                categories) {
            strings[iterCount] = category.getName();
            iterCount++;
        }
        return strings;
    }
}
