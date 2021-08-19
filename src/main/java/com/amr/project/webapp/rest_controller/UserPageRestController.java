package com.amr.project.webapp.rest_controller;

import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.UserPageOrderService;
import com.amr.project.service.abstracts.UserPageShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/userPage")
public class UserPageRestController {
    private UserPageShopService userPageShopService;
    private UserPageOrderService userPageOrderService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserPageRestController(UserPageOrderService userPageOrderService,
                                  UserPageShopService userPageShopService) {
        this.userPageOrderService = userPageOrderService;
        this.userPageShopService = userPageShopService;
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable("userId") Long userId) {
        logger.info("Пользователь с id = " + userId.toString() + " получил список своих заказов");
        return new ResponseEntity<>(userPageOrderService.getOrdersByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/shops/{userId}")
    public ResponseEntity<List<ShopDto>> getShopsByUserId(@PathVariable("userId") Long userId) {
        logger.info("Пользователь с id = " + userId.toString() + " получил список своих магазинов");
        return new ResponseEntity<>(userPageShopService.getShopsByUserId(userId), HttpStatus.OK);
    }

}
