package com.amr.project.service.impl;

import com.amr.project.dao.impl.ItemDAOImpl;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemDAOImpl itemDAO;

    public ItemServiceImpl(ItemDAOImpl itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public List<Item> allItem() {
        return itemDAO.allItem();
    }

    @Override
    public Item itemById(int id) {
        return itemDAO.itemById(id);
    }

    @Override
    public void save(Item item) {
        itemDAO.save(item);
    }

    @Override
    public void update(Item item) {
        itemDAO.update(item);
    }

    @Override
    public void delete(Item item) {
        itemDAO.delete(item);
    }
}
