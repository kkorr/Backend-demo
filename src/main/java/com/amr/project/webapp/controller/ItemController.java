package com.amr.project.webapp.controller;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.converter.ItemMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.DiscountService;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {

    private ItemService itemService;
    private CartItemService cartItemService;
    private UserService userService;
    private ItemMapper itemMapper;
    private CartItemMapper cartItemMapper;
    private ShopMapper shopMapper;
    private DiscountService discountService;

    @Autowired
    public ItemController(ItemService itemService, CartItemService cartItemService, UserService userService,
                          ItemMapper itemMapper, CartItemMapper cartItemMapper, ShopMapper shopMapper, DiscountService discountService) {
        this.itemService = itemService;
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.itemMapper = itemMapper;
        this.cartItemMapper = cartItemMapper;
        this.shopMapper = shopMapper;
        this.discountService = discountService;
    }


    @GetMapping("/product/{id}")
    public String itemById(@PathVariable("id") Long id, Model model) {
        Item item = itemService.getByKey(id);
        Shop shop = item.getShop();
        model.addAttribute("item", itemMapper.itemToItemDto(item));
        model.addAttribute("shop", shopMapper.shopToShopDto(shop));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
             User user = userService.findByUsername(authentication.getName()).get();

            model.addAttribute("discount", discountService.findByUserAndShop(user.getId(), item.getShop().getId()));
            if (cartItemService.findByItemAndShopAndUser(id, user.getId(), item.getShop().getId()).isPresent()) {
                CartItem cartItem = cartItemService.findByItemAndShopAndUser(id, user.getId(), item.getShop().getId()).get();
                model.addAttribute("cartItem", cartItemMapper.cartItemToDto(cartItem));
            }
        }
        return "product_page";
    }
}
