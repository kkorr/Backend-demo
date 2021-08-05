package com.amr.project.converter;

import com.amr.project.model.entity.Category;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper
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
    default Collection<Category> map(String[] strings) {
        if (strings == null) {
            return new ArrayList<>();
        }
        ArrayList<Category> categories = new  ArrayList<>(strings.length);
        int iterCount = 0;
        for (String s : strings) {
            categories.get(iterCount).setName(s);
            iterCount++;
        }
        return categories;
    }
}
