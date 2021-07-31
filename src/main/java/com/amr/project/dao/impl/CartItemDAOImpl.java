package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CartItemDAOImpl extends ReadWriteDAOImpl<CartItem, Long> implements CartItemDao {
    @Override
    public List<CartItem> findByUser(User user) {
        return entityManager.createQuery("SELECT c from CartItem c where c.user.username = :username", CartItem.class)
                .setParameter("username", user.getUsername()).getResultList();
    }

    @Override
    public void deleteByUserAndItem(User user, Item item) {
        entityManager.createQuery("delete from CartItem c where c.user.username = :username and c.item.id = :itemid")
                .setParameter("username", user.getUsername())
                .setParameter("itemid", item.getId())
                .executeUpdate();
    }

    @Override
    public void updateQuantity(int quantity, User user, Item item) {
        entityManager.createQuery("update CartItem c set c.quantity=:newQuantity where c.user.username = :username and c.item.id = :itemid")
                .setParameter("newQuantity", quantity)
                .setParameter("username", user.getUsername())
                .setParameter("itemid", item.getId())
                .executeUpdate();
    }
}
