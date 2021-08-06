package com.amr.project.webapp.rest_controller;


import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.CountryService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop")
public class ShopRestController {
    private ShopService shopService;
    private final ShopMapper shopMapper;
    private final CountryService countryService;
    private final UserService userService;

    @Autowired
    public ShopRestController(ShopService shopService, ShopMapper shopMapper, CountryService countryService, UserService userService) {
        this.shopService = shopService;
        this.shopMapper = shopMapper;
        this.countryService = countryService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> getAddress(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ShopMapper.INSTANCE.shopToShopDto(shopService.getByKey(id)), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Long> saveItem(@RequestBody ShopDto shopDto) {

        Shop shop = shopMapper.shopDtoToShop(shopDto);
        shop.setLocation(countryService.getByName(shopDto.getLocation()));
        shop.setUser(userService.findByUsername(shopDto.getUsername()).get());
        shopService.update(shop);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Long> addItem(@RequestBody  ShopDto shopDto) {
        Shop shop = shopMapper.shopDtoToShop(shopDto);
        shop.setLocation(countryService.getByName(shopDto.getLocation()));
        shop.setUser(userService.findByUsername(shopDto.getUsername()).get());
        shopService.persist(shop);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShopDto> delete(@PathVariable("id") Long id) {
        shopService.deleteByKeyCascadeIgnore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
