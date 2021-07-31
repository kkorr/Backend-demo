package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */
@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop, Long>
        implements ShopService  {

    private final ShopDao shopDao;

    public ShopServiceImpl(ShopDao shopDao) {
        super(shopDao);
        this.shopDao = shopDao;
    }

    @Override
    public Shop findShopById(Long id) {
        return shopDao.findShopById(id);
    }

    @Override
    public Shop findShopByName(String name) {
        return shopDao.findShopByName(name);
    }
}
