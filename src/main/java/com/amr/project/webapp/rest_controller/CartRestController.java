package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.*;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    private final CartItemService cartItemService;
    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public CartRestController(CartItemService cartItemService, UserService userService,
                              ItemService itemService) {
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.itemService = itemService;
    }
    @Transactional
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartItem>> getAllCartItemsByUser(Principal principal) {
        if(principal == null || principal instanceof AnonymousAuthenticationToken) {
            throw new IllegalStateException("Вам нужно авторизоваться для доступа к корзине");
        }
        User user;
        if(userService.findByUsername(principal.getName()).isPresent()) {
            user = userService.findByUsername(principal.getName()).get();
        } else {
            throw new IllegalStateException("Вам нужно авторизоваться для доступа к корзине");
        }

        List<CartItem> cartItems = cartItemService.findByUser(user);

//        List<CartItemDto> cartItemsDto = cartItems.stream().map(c -> CartItemMapper.INSTANCE.cartItemToDto(c)).collect(Collectors.toList());
        return ResponseEntity.ok(cartItems);
    }
    @Transactional
    @PatchMapping("/update/{id}")
    public void updateCartItemQuantity(@PathVariable("id") Long id, @RequestBody CartItem cartItem, Principal principal) {
        if(principal == null || principal instanceof AnonymousAuthenticationToken){
            throw new IllegalStateException("Вам нужно авторизоваться для доступа к корзине");
        }
        cartItemService.getByKey(id).setQuantity(cartItem.getQuantity());
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    public void updateCartItemQuantity(@PathVariable Long id, Principal principal) {
        if(principal == null || principal instanceof AnonymousAuthenticationToken){
            throw new IllegalStateException("Вам нужно авторизоваться для доступа к корзине");
        }
        cartItemService.deleteByKeyCascadeIgnore(id);
    }

}
