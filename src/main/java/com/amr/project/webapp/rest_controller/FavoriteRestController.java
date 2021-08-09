package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Favorite;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.FavoriteService;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteRestController {

    private final UserService userService;
    private final ItemService itemService;
    private final FavoriteService favoriteService;
    private final ItemMapper itemMapper;
    private final ShopMapper shopMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FavoriteRestController(UserService userService, ItemService itemService,
                                  ItemMapper itemMapper, FavoriteService favoriteService, ShopMapper shopMapper) {
        this.userService = userService;
        this.itemService = itemService;
        this.favoriteService = favoriteService;
        this.itemMapper = itemMapper;
        this.shopMapper = shopMapper;
    }

    @Transactional
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDto>> getFavoriteItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            throw new AccessDeniedException("Вам нужно авторизоваться для доступа к избранному");
        }
        User user = userService.findByUsername(authentication.getName()).get();
        Optional<Favorite> optFavorites = favoriteService.findByUser(user);
        Collection<Item> items = null;
        if (optFavorites.isPresent()) {
            Favorite favorites = optFavorites.get();
            items = favorites.getItems();
            LOGGER.info(String.format("Пользователь с id %d успешно получил список избранных товаров в корзине", user.getId()));
        }
        List<ItemDto> itemsDto = items.stream().map(i -> itemMapper.itemToItemDto(i)).collect(Collectors.toList());
        return ResponseEntity.ok(itemsDto);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFavoriteItem(@PathVariable Long id) {
        favoriteService.deleteByKeyCascadeIgnore(id);
        return ResponseEntity.ok().body(null);
    }

}
