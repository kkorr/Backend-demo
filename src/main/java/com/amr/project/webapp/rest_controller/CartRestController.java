package com.amr.project.webapp.rest_controller;

import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.RoleService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    private final CartItemService cartItemService;
    private final UserService userService;

    @Autowired
    public CartRestController(CartItemService cartItemService, UserService userService) {
        this.cartItemService = cartItemService;
        this.userService = userService;
    }

    @GetMapping
    public List<CartItem> getAllCartItemsByUser() {
        User user = userService.findByUsername("ggggggggg").get();
        List<CartItem> cartItems = cartItemService.findByUser(user);
        return cartItems;
    }

}
