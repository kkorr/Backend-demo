package com.amr.project.converter;

import com.amr.project.model.entity.Image;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collection;


@Mapper(componentModel = "spring")
public interface ImageMapper {


    default String[] map(Collection<Image> images) {
        if (images == null) {
            images = new ArrayList<>();
        }
        String[] strings = new String[images.size()];
        int iterCount = 0;
        for (Image image :
                images) {
            strings[iterCount] = image.getUrl();
            iterCount++;
        }
        return strings;
    }
}
