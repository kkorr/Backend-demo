package com.amr.project.service.impl;

import com.amr.project.converter.ItemMapper;
import com.amr.project.dao.abstracts.OrderDao;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl extends ReadWriteServiceImpl<Order, Long> implements OrderService {
    private OrderDao orderDao;
    private ItemMapper itemMapper;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ItemMapper itemMapper) {
        super(orderDao);
        this.orderDao = orderDao;
        this.itemMapper = itemMapper;
    }

    /**
     * Используется на странице OrderRestController метод POST, для сохранения нового заказа
     *
     * @param items
     * @param userOp
     * @return
     */
    @Override
    public Order collectOrderByUserAndItems(List<ItemDto> items, User userOp) {
        Order order = new Order();
        order.setItems(itemMapper.toItems(items));
        order.setAddress(userOp.getAddress());
        order.setUser(userOp);
        order.setBuyerName(userOp.getFirstName());
        order.setBuyerPhone(userOp.getPhone());

        BigDecimal total = items.stream()
                .map(i -> i.getPrice())
                .reduce((s1, s2) -> s1.add(s2))
                .get();
        order.setTotal(total);
        super.persist(order);
        return order;
    }
}
