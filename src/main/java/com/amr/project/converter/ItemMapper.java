package com.amr.project.converter;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(uses = {CategoryMapper.class, ImageMapper.class, ShopMapper.class,
        ReferenceImageMapper.class, ReviewMapper.class})
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mappings({
            @Mapping(source = "shop.id", target = "shopId"),
        //    @Mapping(source = "images", target = "images"),
         //   @Mapping(source = "moderated", target = "moderated"),
         //   @Mapping(source = "moderateAccept", target = "moderateAccept"),
         //   @Mapping(source = "categories", target = "categories")
    })
    public ItemDto itemToItemDto(Item item);

    @Mappings({
         //   @Mapping(source = "categories", target = "categories"),
         //   @Mapping(source = "images", target = "images"),
           // @Mapping(source = "moderated", target = "moderated"),
          //  @Mapping(source = "moderateAccept", target = "moderateAccept"),
          //  @Mapping(source = "moderatedRejectReason", target = "moderatedRejectReason"),
            @Mapping(source = "shopId", target = "shop.id"),
           // @Mapping(source = "pretendentToBeDeleted", target = "pretendentToBeDeleted")
    })
    Item itemDtoToItem(ItemDto itemDto);



    default List<Item> map(Shop shop) {
        return shop.getItems();
    }
}
