package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.*;
import org.mapstruct.control.MappingControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final CartItemMapper cartItemMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CartRestController(CartItemService cartItemService, UserService userService,
                              ItemService itemService, CartItemMapper cartItemMapper) {
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.itemService = itemService;
        this.cartItemMapper = cartItemMapper;
    }
    @Transactional
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartItemDto>> getAllCartItemsByUser(Principal principal) {
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
        List<CartItemDto> cartItemsDto = cartItems.stream().map(c -> cartItemMapper.cartItemToDto(c)).collect(Collectors.toList());
        LOGGER.info(String.format("Пользователь с id %d успешно открыл корзину покупателя", user.getId()));
        return ResponseEntity.ok(cartItemsDto);
    }
    @Transactional
    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateCartItemQuantity(@PathVariable("id") Long id, @RequestBody CartItemDto cartItem) {
        cartItemService.getByKey(id).setQuantity(cartItem.getQuantity());
        return ResponseEntity.ok().body(null);
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> updateCartItemQuantity(@PathVariable Long id) {
        cartItemService.deleteByKeyCascadeIgnore(id);
        return ResponseEntity.ok().body(null);
    }

}
