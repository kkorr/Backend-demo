package com.amr.project.converter;

import com.amr.project.model.entity.Image;
import com.amr.project.service.abstracts.ImageService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ReferenceImageMapper {
    private final
    ImageService imageService;

    public ReferenceImageMapper(ImageService imageService) {
        this.imageService = imageService;
    }

    public Image map(String name) {
        return imageService.getByName(name);
    }

    public Collection<Image> map(String[] images) {
        return Arrays.stream(images)
                .map(imageService::getByName)
                .collect(Collectors.toList());
    }
}
