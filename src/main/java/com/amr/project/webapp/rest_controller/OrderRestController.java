package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.converter.OrderMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.OrderService;
import com.amr.project.service.abstracts.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final UserService userService;

    @Autowired
    public OrderRestController(OrderService orderService,
                               OrderMapper orderMapper,
                               ItemMapper itemMapper,
                               UserService userService) {
        this.userService = userService;
        this.itemMapper = itemMapper;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(orderMapper.orderToDto(orderService.getByKey(orderId)),
                HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<OrderDto> saveOrder(@RequestBody List<ItemDto> items) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOp = userService.findByUsername(authentication.getName());

        if(authentication.isAuthenticated() && userOp.isPresent()) {
            Order order = orderService.collectOrderByUserAndItems(items, userOp.get());
            return new ResponseEntity<>(orderMapper.orderToDto(order),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Long> updateOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.dtoToOrder(orderDto);
        orderService.persist(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderDto> delete(@PathVariable("orderId") Long id) {
        orderService.deleteByKeyCascadeIgnore(id);
        LOGGER.info(String.format("Пользователь удалил заказ с id %d", orderService.getByKey(id)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
