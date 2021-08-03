package com.amr.project.converter;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper ITEM_MAPPER_INSTANCE = Mappers.getMapper(ItemMapper.class);
    ItemDto itemToDto(Item item);
    Item DtoToItem(ItemDto itemDto);
}
