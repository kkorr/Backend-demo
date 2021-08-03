package com.amr.project.service.impl;

import com.amr.project.converter.ItemMapper;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShopService {

    @Autowired
    public ShopServiceImpl(@Qualifier("readWriteDAOImpl") ReadWriteDAO<Shop, Long> readWriteDAO) {
        super(readWriteDAO);
    }

    @Override
    public List<ItemDto> getPopularItemsByMarketId(Long id) {
        Shop shop = super.getByKey(id);
        List<Item> items = shop.getItems();

        /*Сортировка товаров по полю count*/
        boolean isSorted = false;
        Item buf = null;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < items.size() - 1; i++) {
                if(items.get(i).getCount() < items.get(i+1).getCount()){
                    isSorted = false;
                    buf = items.get(i);
                    items.set(i, items.get(i + 1));
                    items.set(i+1, buf);
                }
            }
        }

        /*первые 10 товаров приводим к списку ItemDto*/
        List<ItemDto> listItemDto = new ArrayList<>();
        for(Item i : items.subList(0,10)) {
            listItemDto.add(ItemMapper.ITEM_MAPPER_INSTANCE.itemToDto(i));
        }

        return listItemDto;
    }

    @Override
    public List<ItemDto> getItemsByMarketId(Long id) {
        List<ItemDto> listItemDto = new ArrayList<>();
        for(Item i : super.getByKey(id).getItems()) {
            listItemDto.add(ItemMapper.ITEM_MAPPER_INSTANCE.itemToDto(i));
        }
        return listItemDto;
    }

    @Override
    public String getDescriptionByMarketId(Long id) {
        return super.getByKey(id).getDescription();
    }
}
