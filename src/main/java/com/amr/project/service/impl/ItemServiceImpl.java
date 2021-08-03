package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ReadWriteDAO;
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
    public ItemServiceImpl(ReadWriteDAO<Item, Long> readWriteDAO, ItemDao itemDao) {
        super(readWriteDAO);
        this.itemDao = itemDao;
    }

    @Override
    public List<Item> getItemsByShopId(Long shopId) {
        return itemDao.getItemsByShopId(shopId);
    }

    @Override
    public List<Item> getPopularItemsByShopId(Long shopId) {
        List<Item> listItems = getItemsByShopId(shopId);


        boolean isSorted = false;
        Item buf;
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
