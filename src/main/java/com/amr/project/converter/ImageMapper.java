package com.amr.project.converter;

import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Image;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper
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

    default Collection<Image> map(String[] strings) {
        if (strings == null) {
            return new ArrayList<>();
        }
        ArrayList<Image> images = new  ArrayList<>(strings.length);
        int iterCount = 0;
        for (String s : strings) {
            images.get(iterCount).setUrl(s);
            iterCount++;
        }
        return images;
    }

    default String map(Image image) {
        return image != null ? image.getUrl() : "No image!";
    }
}
