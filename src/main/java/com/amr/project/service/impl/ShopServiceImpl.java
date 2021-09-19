package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShopService {

    private final ShopDao shopDao;

    @Autowired
    public ShopServiceImpl(ShopDao shopDao) {
        super(shopDao);
        this.shopDao = shopDao;
    }

    @Override
    public Shop findShopByName(String name) {
        return shopDao.findShopByName(name);
    }

    @Override
    public List<Shop> getUnmoderatedShops() {
        return shopDao.findUnmoderatedShops();
    }

    @Override
    public List<Shop> findModeratedShops() {
        return shopDao.findModeratedShops();
    }
}
