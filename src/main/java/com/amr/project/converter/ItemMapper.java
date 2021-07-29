package com.amr.project.converter;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author denisqaa on 29.07.2021.
 * @project platform
 */

@Mapper(uses = {CategoryMapper.class, ImageMapper.class, ShopMapper.class})
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "count", target = "count"),
            @Mapping(source = "rating", target = "rating"),
            @Mapping(source = "categories", target = "categories"),
            @Mapping(source = "images", target = "images"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "discount", target = "discount"),
            @Mapping(source = "moderated", target = "moderated"),
            @Mapping(source = "moderateAccept", target = "moderateAccept"),
            @Mapping(source = "moderatedRejectReason", target = "moderatedRejectReason"),
            @Mapping(source = "shop", target = "shopId"),
            @Mapping(source = "pretendentToBeDeleted", target = "pretendentToBeDeleted")
    })
    ItemDto itemToItemDto(Item item);

    default List<Item> map(Shop shop) {
        return shop.getItems();
    }
}
