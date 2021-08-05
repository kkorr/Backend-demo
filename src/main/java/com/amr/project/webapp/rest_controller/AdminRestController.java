package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.*;
import com.amr.project.model.dto.*;
import com.amr.project.service.abstracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private ShopService shopService;
    private UserService userService;
    private ItemService itemService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RoleService roleService;


    @Autowired
    public AdminRestController(ShopService shopService, UserService userService, ItemService itemService) {
        this.shopService = shopService;
        this.userService = userService;
        this.itemService = itemService;
    }


    @GetMapping("/allshops")
    public ResponseEntity<List<ShopDto>> showAllShops() {
        List<ShopDto> shops = shopService.getAll()
                .stream()
                .map(x->ShopMapper.INSTANCE.shopToShopDto(x))
                .collect(Collectors.toList());

        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<UserDto>> showAllUsers() {
        List<UserDto> users = userService.getAll()
                .stream()
                .map(x-> UserMapper.INSTANCE.userToDto(x))
                .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/allitems")
    public ResponseEntity<List<ItemDto>> showAllItems() {
        List<ItemDto> items = itemService.getAll()
                .stream()
                .map(x-> ItemMapper.INSTANCE.itemToItemDto(x))
                .collect(Collectors.toList());

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/allcountries")
    public ResponseEntity<List<CountryDto>> showAllCountries() {
        List<CountryDto> countries = countryService.getAll()
                .stream()
                .map(x-> CountryMapper.INSTANCE.countryToDto(x))
                .sorted((x,y)->(x.getId().compareTo(y.getId())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/allcities")
    public ResponseEntity<List<CityDto>> showAllCities() {
        List<CityDto> cities = cityService.getAll()
                .stream()
                .map(x-> CityMapper.INSTANCE.cityToDto(x))
                .sorted((x,y)->(x.getId().compareTo(y.getId())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/alladdresses")
    public ResponseEntity<List<AddressDto>> showAllAddresses() {
        List<AddressDto> addresses = addressService.getAll()
                .stream()
                .map(x-> AddressMapper.INSTANCE.addressToDto(x))
                .collect(Collectors.toList());

        return new ResponseEntity<>(addresses, HttpStatus.OK);

    }

    @GetMapping("/allcategories")
    public ResponseEntity<List<CategoryDto>> showAllCategories() {
        List<CategoryDto> categories = categoryService.getAll()
                .stream()
                .map(x-> CategoryMapper.INSTANCE.categoryToDto(x))
                .sorted((x,y)->(x.getId().compareTo(y.getId())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/allroles")
    public ResponseEntity<List<RoleDto>> showAllRoles() {
        List<RoleDto> roles = roleService.getAll()
                .stream()
                .map(x-> RoleMapper.INSTANCE.roleToDto(x))
                .collect(Collectors.toList());

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
