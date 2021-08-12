package com.amr.project.webapp.rest_controller.moderator_rest;

import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CountryService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author denisqaa on 09.08.2021.
 * @project platform
 */

@RestController
@RequestMapping("/moderator/api/shops")
public class ModeratorShopsRestController {

    private final ShopService shopService;

    private final ShopMapper shopMapper;

    private final CountryService countryService;

    private final UserService userService;

    public ModeratorShopsRestController(ShopService shopService, ShopMapper shopMapper, CountryService countryService, UserService userService) {
        this.shopService = shopService;
        this.shopMapper = shopMapper;
        this.countryService = countryService;
        this.userService = userService;
    }


    @Operation(
            summary = "Получение немодерированных магазинов",
            description = "Возвращает все магазины у которых флаг isModerated = false"
    )
    @GetMapping("/getUnmoderatedShops")
    public ResponseEntity<List<ShopDto>> getUnmoderatedShops() {
        return new ResponseEntity<>(
                shopService
                        .getUnmoderatedShops()
                        .stream().map(shopMapper::shopToShopDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }


    @GetMapping("/getOneUnmoderatedShop/{id}")
    public ResponseEntity<ShopDto> getOneUnmoderatedItem(@PathVariable("id") Long id) {
        return shopService.getByKey(id).isModerated() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(shopMapper.shopToShopDto(shopService.getByKey(id)), HttpStatus.OK);
    }

    @PutMapping("/editShop")
    public ResponseEntity<ShopDto> editItem(@RequestBody ShopDto shopDto) {
        Shop shop = shopMapper.shopDtoToShop(shopDto);
        shop.setLocation(countryService.getByName(shopDto.getLocation()));
        Optional<User> user = userService.findByUsername(shopDto.getUsername());
        if (user.isPresent()) {
            shop.setUser(user.get());
        } else {
            shop.setUser(new User());
        }
        shopService.update(shop);
        return new ResponseEntity<>(shopMapper.shopToShopDto(shopService.getByKey(shopDto.getId())), HttpStatus.OK);
    }

    @GetMapping("/getUnmoderatedShopsCount")
    public ResponseEntity<Long> getUnmoderatedItemsCount() {
        return new ResponseEntity<>((long) shopService
                .getUnmoderatedShops()
                .size(),
                HttpStatus.OK);
    }
}
