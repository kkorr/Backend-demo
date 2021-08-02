package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShopService {

    private final ShopDao shopDao;

    @Autowired
    public ShopServiceImpl(ReadWriteDAO<Shop, Long> readWriteDAO, ShopDao shopDao) {
        super(readWriteDAO);
        this.shopDao = shopDao;
    }
}
