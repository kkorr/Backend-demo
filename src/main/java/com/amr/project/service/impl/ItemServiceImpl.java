package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ItemService {

    private final ItemDao itemDao;

    @Autowired
    public ItemServiceImpl(ReadWriteDAO<Item, Long> readWriteDAO, ItemDao itemDao) {
        super(readWriteDAO);
        this.itemDao = itemDao;
    }
}
