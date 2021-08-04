package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.CityMapper;
import com.amr.project.converter.ItemMapper;
import com.amr.project.model.dto.CityDto;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {

    ItemService itemService;

    @Autowired
    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getAddress(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ItemMapper.INSTANCE.itemToDto(itemService.getByKey(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDto> delete(@PathVariable("id") Long id) {
        itemService.deleteByKeyCascadeIgnore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
