package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.dao.abstracts.DiscountDao;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl extends ReadWriteServiceImpl<Discount, Long> implements DiscountService {

    private final DiscountDao discountDao;

    @Autowired
    public DiscountServiceImpl(DiscountDao discountDao) {
        super(discountDao);
        this.discountDao = discountDao;
    }

    @Override
    public List<Discount> findByUser(User user) {
        return discountDao.findByUser(user);
    }

    @Override
    public List<Discount> findByShop(Shop shop) {
        return discountDao.findByShop(shop);
    }

    @Override
    public Optional<Discount> findByUserAndShop(Long userId, Long shopId) {
        return discountDao.findByUserAndShop(userId, shopId);
    }
}
