package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Order;

public interface OrderDao extends ReadWriteDAO<Order, Long> {
//    Order findOrderById(Long id);
//
//    Order findOrderByName(String name);
//
//    List<Order> getOrdersById(Long id);
    void deleteItemInOrder(Long orderId, Long itemId);
}
