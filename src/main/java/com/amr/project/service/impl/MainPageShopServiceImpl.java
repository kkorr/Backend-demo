package com.amr.project.service.impl;

import com.amr.project.converter.ShopsMapper;
import com.amr.project.dao.abstracts.MainPageShopsDao;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.MainPageShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainPageShopServiceImpl extends ReadWriteServiceImpl<Shop,Long> implements MainPageShopService {
    private final MainPageShopsDao mainPageShopsDAO;

    @Autowired
    public MainPageShopServiceImpl(MainPageShopsDao mainPageShopsDAO) {
        super(mainPageShopsDAO);
        this.mainPageShopsDAO = mainPageShopsDAO;
    }


    @Override
    public List<ShopDto> findPopularShops() {
        return ShopsMapper.INSTANCE.toShopDto(mainPageShopsDAO.getAll().stream()
                .sorted(Comparator.comparing(Shop::getCount,Comparator.reverseOrder()))
                .limit(10).collect(Collectors.toList()));
    }
}
