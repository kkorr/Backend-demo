package com.amr.project.webapp.controller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.dao.abstracts.ShowcaseCategoryDao;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер страницы витрины магазина
 */
@Controller
@RequestMapping("/showcase/{shopId}")
public class ShowcaseController {

    private ShowcaseShopService showcaseShopService;
    private ShowcaseItemService showcaseItemService;
    private ShowcaseCategoryService showcaseCategoryService;
    private UserService userService;
    @Autowired
    public ShowcaseController(ShowcaseItemService showcaseItemService,
                              ShowcaseShopService showcaseShopService,
                              ShowcaseCategoryService showcaseCategoryService,
                              UserService userService) {

        this.showcaseCategoryService = showcaseCategoryService;
        this.showcaseItemService = showcaseItemService;
        this.showcaseShopService = showcaseShopService;
        this.userService = userService;
    }
    public ShowcaseController() {}


    @GetMapping
    public String getShowcaseView(Model model, @PathVariable("shopId") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsername(authentication.getName());
        if(authentication.isAuthenticated() && user.isPresent()) {
            model.addAttribute("user", user.get());
        }
        model.addAttribute("shop", showcaseShopService.getByKey(id));
        model.addAttribute("categories", showcaseCategoryService.findCategoryByShopId(id));
        return "showcase";
    }

    @GetMapping("/popular")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> getPopularItems(@PathVariable("shopId") Long marketId) {
        return new ResponseEntity<>(showcaseItemService.findPopularItemsByShopId(marketId), HttpStatus.OK);
    }
    @GetMapping("/items")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> getItems(@PathVariable("shopId") Long marketId) {
        return new ResponseEntity<>(showcaseItemService.findItemsByShopId(marketId), HttpStatus.OK);
    }
    @GetMapping("/description")
    @ResponseBody
    public ResponseEntity<ShopDto> getDescription(@PathVariable("shopId") Long marketId) {
        return new ResponseEntity<>(showcaseShopService.getDtoByKey(marketId), HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> getItemsByCategory(@PathVariable("shopId") Long shopId,
                                                            @PathVariable("categoryId") Long categoryId) {
        return new ResponseEntity<>(showcaseItemService.findItemsByCategoryIdAndShopId(categoryId,shopId), HttpStatus.OK);
    }
    @GetMapping("/search/{itemName}")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> searchItemsByName(@PathVariable("shopId") Long shopId,
                                                           @PathVariable("itemName") String itemName) {
        return new ResponseEntity<>(showcaseItemService.searchItemsByName(shopId,itemName),HttpStatus.OK);
    }

}
