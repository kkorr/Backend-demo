package com.amr.project.converter;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface ItemsMapper {
    ItemsMapper INSTANCE = Mappers.getMapper(ItemsMapper.class);
    List<ItemDto> toItemsDto(List<Item> item);
}
