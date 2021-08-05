package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.CategoryService;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {

    ItemService itemService;
    CategoryService categoryService;
    ShopService shopService;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemRestController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getAddress(@PathVariable("id") Long id) {
        ItemDto itemDto = ItemMapper.INSTANCE.itemToItemDto(itemService.getByKey(id));
        String[] strings = {"Одежда"};
        itemDto.setCategories(strings);

       // itemService.getByKey(id).getCategories().stream().map(x-> x.getName()).collect(Collectors.toList());

        //itemDto.setCategories(itemService.getByKey(id).getCategories().stream().map(x-> x.getName()).collect(Collectors.toList()));
      //  return new ResponseEntity<>(ItemMapper.INSTANCE.itemToItemDto(itemService.getByKey(id)), HttpStatus.OK);
       // return new ResponseEntity<>(itemService.getByKey(id), HttpStatus.OK);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Long> saveItem(@RequestBody ItemDto itemDto) {
/*        Address address = AddressMapper.INSTANCE.dtoToAddress(addressDto);
        address.setCity(cityService.getByName(addressDto.getCity()));
        address.setCountry(cityService.getByName(addressDto.getCity()).getCountry());
        addressService.update(address);*/
        Item item = ItemMapper.INSTANCE.itemDtoToItem(itemDto);
        Set<Category> categories = new HashSet<>();
        Arrays.stream(itemDto.getCategories()).peek(x-> categories.add(categoryService.getByName(x))).close();

        item.setCategories(categories);
        item.setShop(shopService.getByKey(1L));
        itemService.update(item);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Long> addItem(@RequestBody  ItemDto itemDto) {
        Item item = ItemMapper.INSTANCE.itemDtoToItem(itemDto);
        Set<Category> categories = new HashSet<>();
        Arrays.stream(itemDto.getCategories()).peek(x-> categories.add(categoryService.getByName(x))).close();

        item.setCategories(categories);
        item.setShop(shopService.getByKey(1L));
        itemService.persist(item);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDto> delete(@PathVariable("id") Long id) {
        itemService.deleteByKeyCascadeIgnore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
