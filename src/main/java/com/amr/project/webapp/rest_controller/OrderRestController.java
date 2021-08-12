package com.amr.project.webapp.rest_controller;

import com.amr.project.model.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("orderId") String orderId) {
        return null;
    }

    @PutMapping("/save")
    public ResponseEntity<Long> saveOrder(@RequestBody OrderDto orderDto) {
        System.out.println("orderDto" + orderDto);

//        Order order = orderMapper.orderDtoToOrder(orderDto);
//        orderService.update(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addOrder(@RequestBody OrderDto orderDto) {
//        String[] strings = {};
//        orderDto.setImages(strings);
//        orderDto.setReviews(strings);
//        System.out.println("orderDto1" + orderDto);
//
//        Orde order = orderMapper.orderDtoToOrder(orderDto);
//        orderService.persist(order);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDto> delete(@PathVariable("id") Long id) {
//        orderService.deleteByKeyCascadeIgnore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
