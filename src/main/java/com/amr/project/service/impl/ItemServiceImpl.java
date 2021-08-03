package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */
@Service
@Transactional
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
}

