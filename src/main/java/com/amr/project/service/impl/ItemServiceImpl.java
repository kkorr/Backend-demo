package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.dao.impl.ItemDaoImpl;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ItemService {

    private final ItemDaoImpl itemDao;

    @Autowired
    public ItemServiceImpl(ReadWriteDAO<Item, Long> readWriteDAO, ItemDao itemDao) {
        super(readWriteDAO);
        this.itemDao = itemDao;
    }

    @Override
    public void persist(Item item) {
        itemDao.persist(item);
    }

    @Override
    public void update(Item item) {
        itemDao.update(item);
    }

    @Override
    public void delete(Item item) {
        itemDao.delete(item);
    }

    @Override
    public Item getByKey(Long id) {
        return itemDao.getByKey(id);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public Item getByName(String name) {
        return itemDao.getByName(name);
    }
}
