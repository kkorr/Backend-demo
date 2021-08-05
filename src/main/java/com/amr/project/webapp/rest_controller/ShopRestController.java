package com.amr.project.webapp.rest_controller;


import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop")
public class ShopRestController {
    private ShopService shopService;

    @Autowired
    public ShopRestController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> getAddress(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ShopMapper.INSTANCE.shopToShopDto(shopService.getByKey(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShopDto> delete(@PathVariable("id") Long id) {
        shopService.deleteByKeyCascadeIgnore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
