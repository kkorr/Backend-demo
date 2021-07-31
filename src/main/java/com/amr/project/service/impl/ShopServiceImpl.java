package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShopService {
    private ShopDao shopDao;

    @Autowired
    public ShopServiceImpl(ShopDao shopDao) {
        super(shopDao);
        this.shopDao = shopDao;
    }

    @Override
    public List<Item> getPopularItemsByMarketId(Long id) {
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
        return items.subList(0, 10);
    }
}
