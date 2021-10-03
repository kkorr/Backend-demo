package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.User;
import java.util.List;
import java.util.Optional;

public interface CartItemDao extends ReadWriteDAO<CartItem, Long> {

    List<CartItem> findByUser(User user);

    void deleteByUserAndItem(Long userId, Long itemId);

    void updateQuantity(int quantity, Long userId, Long itemId);

    Optional<CartItem> findByItemAndShopAndUser(Long itemId, Long userId, Long shopId);

    Optional<CartItem> findByItemAndShopAndCookie(Long itemId, Long shopId, String cookie);
}
