package com.amr.project.converter;

import com.amr.project.model.entity.Image;
import com.amr.project.service.abstracts.ImageService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring")
public abstract class ReferenceImageMapper {
    @Autowired
    private ImageService imageService;

    public Image map(String name) {
        return imageService.getByName(name);
    }

    public Collection<Image> map(String[] images) {
        return Arrays.stream(images)
                .map(imageService::getByName)
                .collect(Collectors.toList());
    }
}
