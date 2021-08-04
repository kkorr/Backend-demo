package com.amr.project.service.impl;


import com.amr.project.converter.ItemMapper;
import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ItemService {

    private final ItemDao itemDao;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao) {
        super(itemDao);
        this.itemDao = itemDao;
    }
    @Override
    public Item findItemById(Long id) {
        return itemDao.findItemById(id);
    }

    @Override
    public Item findItemByName(String name) {
        return itemDao.findItemByName(name);
    }

    @Override
    public List<ItemDto> getItemsByShopId(Long shopId) {
        List<ItemDto> itemsDto = new ArrayList<>();
        for(Item i : itemDao.getItemsByShopId(shopId)) {
            itemsDto.add(ItemMapper.INSTANCE.itemToItemDto(i));
        }
        return itemsDto;
    }

    @Override
    public List<ItemDto> getPopularItemsByShopId(Long shopId) {
        List<ItemDto> listItems = getItemsByShopId(shopId);

        /*сортировка Item по полю count*/
        boolean isSorted = false;
        ItemDto buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < listItems.size() - 1; i++) {
                if(listItems.get(i).getCount() < listItems.get(i+1).getCount()){
                    isSorted = false;

                    buf = listItems.get(i);
                    listItems.add(i, listItems.get(i+1));
                    listItems.add(i+1, buf);
                }
            }
        }

        return listItems.subList(0,10);
    }
}
