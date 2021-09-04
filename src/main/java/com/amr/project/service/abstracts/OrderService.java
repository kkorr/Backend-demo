package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.User;

import java.util.List;

public interface OrderService extends ReadWriteService<Order, Long> {
    Order collectOrderByUserAndItems(List<ItemDto> items, User userOp);
    void updateAddressAndUserInfo(Long id, OrderDto orderDto);
    void deleteItemInOrder(Long orderId, Long itemId);
}
