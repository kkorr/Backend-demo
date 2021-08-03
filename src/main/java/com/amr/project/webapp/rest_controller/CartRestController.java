package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.RoleService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
    @GetMapping
    public List<CartItem> getAllCartItemsByUser() {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails)principal).getUsername();
//        } else {
//            String username = principal.toString();
//        }
        // УДАЛИТЬ
        User user = userService.findByUsername("ggggggggg").get();
        List<CartItem> cartItems = cartItemService.findByUser(user);
//        List<CartItemDto> cartItemsDto = cartItems.stream().map(c -> CartItemMapper.INSTANCE.cartItemToDto(c)).collect(Collectors.toList());
        return cartItems;
    }
    @Transactional
    @PatchMapping("/update/{id}")
    public void updateCartItemQuantity(@PathVariable("id") Long id, @RequestBody CartItem cartItem) {
//            @RequestParam int quantity, @RequestParam(name = "user") Long userId,
//                                       @RequestParam(name = "item") Long itemId) {
        cartItemService.getByKey(id).setQuantity(cartItem.getQuantity());
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    public void updateCartItemQuantity(@PathVariable Long id) {
        cartItemService.deleteByKeyCascadeIgnore(id);
    }

}
