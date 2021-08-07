package com.amr.project.converter;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import org.mapstruct.factory.Mappers;

public interface ItemsMapper {
    ItemsMapper INSTANCE = Mappers.getMapper(ItemsMapper.class);
    ItemDto toItemsDto(Item item);
}
