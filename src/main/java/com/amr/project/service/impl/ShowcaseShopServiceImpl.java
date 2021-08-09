package com.amr.project.service.impl;

import com.amr.project.converter.ShopMapper;
import com.amr.project.dao.abstracts.ShowcaseShopDao;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShowcaseShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShowcaseShopServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShowcaseShopService {
    private ShowcaseShopDao showcaseShopDao;
    private ShopMapper shopMapper;

    @Autowired
    public ShowcaseShopServiceImpl(ShowcaseShopDao showcaseShopDao,
                                   ShopMapper shopMapper) {
        super(showcaseShopDao);
        this.showcaseShopDao = showcaseShopDao;
        this.shopMapper = shopMapper;
    }

    @Override
    public ShopDto getDtoByKey(Long id) {
        return shopMapper.shopToShopDto(super.getByKey(id));
    }
}
