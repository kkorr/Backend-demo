package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
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
    public void save(Item item) {
        itemDao.save(item);
    }

    @Override
    public void delete(Long id) {
        itemDao.delete(itemDao.findItemById(id));
    }

    @Override
    public void update(Item item) {
        itemDao.save(item);
    }

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }
}
