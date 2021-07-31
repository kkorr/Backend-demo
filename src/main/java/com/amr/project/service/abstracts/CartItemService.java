package com.amr.project.service.abstracts;

import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.User;

import java.util.List;

public interface CartItemService extends ReadWriteService<CartItem, Long> {

    List<CartItem> findByUser(User user);

    void deleteByUserAndItem(User user, Item item);

    void updateQuantity(int quantity, User user, Item item);
}
