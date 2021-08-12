package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.OrderMapper;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order;
import com.amr.project.service.abstracts.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderRestController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(orderMapper.orderToDto(orderService.getByKey(orderId)),
                HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Long> saveOrder(@RequestBody OrderDto orderDto) {

        Order order = orderMapper.dtoToOrder(orderDto);
        orderService.update(order);
        LOGGER.info(String.format("Пользователь изменил данные заказа", orderDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.dtoToOrder(orderDto);
        orderService.persist(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDto> delete(@PathVariable("id") Long id) {
        orderService.deleteByKeyCascadeIgnore(id);
        LOGGER.info(String.format("Пользователь удалил заказ с id %d", orderService.getByKey(id)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
