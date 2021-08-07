package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */

@Service
public class ItemServiceImpl extends ReadWriteServiceImpl<Item, Long>
        implements ItemService {
    private final ItemDao itemDao;

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
    public List<Item> getItemsByShopId(Long id) {
        return itemDao.getItemsByShopId(id);
    }
}

