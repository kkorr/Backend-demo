package com.amr.project.service.impl;

import com.amr.project.converter.ShopsMapper;
import com.amr.project.dao.abstracts.ShopRepository;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.MainPageShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MainPageShopServiceImpl implements MainPageShopService {
    private final ShopRepository shopRepository;

    @Autowired
    public MainPageShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public List<ShopDto> findPopularShops() {
        List<Shop> allShop = shopRepository.findAll();
        Stream<Shop> popularShops = allShop.stream().sorted(Comparator.comparing(Shop::getRating,
                Comparator.reverseOrder())).limit(10);
        List<Shop> shopsList = popularShops.collect(Collectors.toList());
        List<ShopDto> shopsDto = new ArrayList<>();
        for (Shop shop : shopsList) {
            shopsDto.add(ShopsMapper.INSTANCE.toShopDto(shop));
        }
        return shopsDto;
    }
}
