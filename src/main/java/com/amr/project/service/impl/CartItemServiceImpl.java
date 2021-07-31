package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.dao.abstracts.RoleDao;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl extends ReadWriteServiceImpl<CartItem, Long> implements CartItemService {

    private final CartItemDao cartItemDao;

    @Autowired
    public CartItemServiceImpl(CartItemDao cartItemDao) {
        super(cartItemDao);
        this.cartItemDao = cartItemDao;
    }

    @Override
    public List<CartItem> findByUser(User user) {
        return cartItemDao.findByUser(user);
    }

    @Override
    public void deleteByUserAndItem(User user, Item item) {
        cartItemDao.deleteByUserAndItem(user, item);
    }

    @Override
    public void updateQuantity(int quantity, User user, Item item) {
        cartItemDao.updateQuantity(quantity, user, item);
    }
}
