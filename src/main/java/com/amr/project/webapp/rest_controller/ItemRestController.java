package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/item")
public class ItemRestController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemRestController(ItemService itemService, ItemMapper itemMapper ) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getAddress(@PathVariable("id") Long id) {
        return new ResponseEntity<>(itemMapper.itemToItemDto(itemService.getByKey(id)), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Long> saveItem(@RequestBody ItemDto itemDto) {
        System.out.println("itemDto" + itemDto);
        itemDto.setImages(itemMapper.itemToItemDto(itemService.getByKey(itemDto.getId())).getImages());
        itemDto.setReviews(itemMapper.itemToItemDto(itemService.getByKey(itemDto.getId())).getReviews());

        Item item = itemMapper.itemDtoToItem(itemDto);
        itemService.update(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Long> addItem(@RequestBody  ItemDto itemDto) {
        String[] strings = {};

        itemDto.setImages(strings);
        itemDto.setReviews(strings);
        System.out.println("itemDto1" + itemDto);

        Item item = itemMapper.itemDtoToItem(itemDto);
        itemService.persist(item);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDto> delete(@PathVariable("id") Long id) {
        itemService.deleteByKeyCascadeIgnore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
