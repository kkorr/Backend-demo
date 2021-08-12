package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.DiscountMapper;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.Favorite;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/discounts")
public class DiscountRestController {

    private final UserService userService;
    private final DiscountService discountService;
    private final ShopService shopService;
    private final DiscountMapper discountMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public DiscountRestController(UserService userService, DiscountService discountService, DiscountMapper discountMapper,
                                  ShopService shopService) {
        this.userService = userService;
        this.discountService = discountService;
        this.shopService = shopService;
        this.discountMapper = discountMapper;
    }

    @GetMapping
    public ResponseEntity<List<DiscountDto>> getDiscounts(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
            throw new AccessDeniedException("Вам нужно авторизоваться для доступа к списку скидок");
        }
        User user = userService.findByUsername(authentication.getName()).get();
        List<Discount> discounts = discountService.findByUser(user);
        if(discounts == null) {
            LOGGER.info(String.format("Пользователь с id %d получил пустой список скидок", user.getId()));
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        List<DiscountDto> discountsDto = discounts.stream().map(d -> discountMapper.toDto(d))
                .filter(s-> (s.getFixedDiscount()) !=0 || (s.getPercentage() !=0))
                .collect(Collectors.toList());
        LOGGER.info(String.format("Пользователь с id %d получил список скидок", user.getId()));
        return ResponseEntity.ok().body(discountsDto);
    }
    @GetMapping("/add")
    public ResponseEntity<String[]> getOwnedShops(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
            throw new AccessDeniedException("Вам нужно авторизоваться для создания скидки");
        }
        User shopOwner = userService.findByUsername(authentication.getName()).get();
        Collection<Shop> shops = shopOwner.getShops();
        if (shops.isEmpty()) {
            return ResponseEntity.ok().body(new String[]{});
        } else {
            String[] shopsString = shops.stream().map(s -> s.getName()).toArray(String[]::new);
            return ResponseEntity.ok().body(shopsString);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<Void> createDiscount(@RequestBody DiscountDto discountDto, @RequestParam String[] shops){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
            throw new AccessDeniedException("Вам нужно авторизоваться для создания скидки");
        }
        for (String s:shops) {
            Shop shop = shopService.findShopByName(s);
            discountDto.setShopId(shop.getId());
            discountService.persist(discountMapper.fromDto(discountDto));
        }
        return ResponseEntity.ok().body(null);
    }
}
