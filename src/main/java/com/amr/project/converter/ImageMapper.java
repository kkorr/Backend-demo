package com.amr.project.converter;

import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Component
@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto imageToDto(Image image);

    Image dtoToImage(ImageDto imageDto);

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

