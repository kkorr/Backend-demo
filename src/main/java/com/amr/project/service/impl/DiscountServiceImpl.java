package com.amr.project.service.impl;

import com.amr.project.converter.DiscountMapper;
import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.dao.abstracts.DiscountDao;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl extends ReadWriteServiceImpl<Discount, Long> implements DiscountService {

    private final DiscountDao discountDao;
    private final DiscountMapper discountMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public DiscountServiceImpl(DiscountDao discountDao, DiscountMapper discountMapper) {
        super(discountDao);
        this.discountDao = discountDao;
        this.discountMapper = discountMapper;
    }


    public List<DiscountDto> findByUser(User user) {
        List<Discount> discounts = discountDao.findByUser(user);
        if(discounts == null) {
            LOGGER.info(String.format("Пользователь с id %d получил пустой список скидок", user.getId()));
            return new ArrayList<>();
        }
        List<DiscountDto> discountsDto = discounts.stream().map(d -> discountMapper.toDto(d))
                .filter(s-> (s.getFixedDiscount()) !=0 || (s.getPercentage() !=0))
                .collect(Collectors.toList());
        LOGGER.info(String.format("Пользователь с id %d получил список скидок", user.getId()));
        return discountsDto;
    }

    @Override
    public List<DiscountDto> findByShop(Shop shop) {
        List<Discount> discounts = discountDao.findByShop(shop);
        if(discounts == null) {
            return new ArrayList<>();
        }
        List<DiscountDto> discountsDto = discounts.stream().map(d -> discountMapper.toDto(d))
                .filter(s-> (s.getFixedDiscount()) !=0 || (s.getPercentage() !=0))
                .collect(Collectors.toList());
        return discountsDto;
    }

    @Override
    public DiscountDto findByUserAndShop(Long userId, Long shopId) {
        Optional<Discount> discount = discountDao.findByUserAndShop(userId, shopId);
        if (!discount.isPresent()){
            throw new IllegalArgumentException(String.format("Скидки для пользователя с Id %d и Id магазина %d не существует",
                    userId, shopId));
        };
        return discountMapper.toDto(discount.get());
    }
}
