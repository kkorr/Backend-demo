package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.AddressMapper;
import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.AddressService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressRestController {

    private AddressService addressService;
    private UserService userService;

    @Autowired
    public AddressRestController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable("id") Long id) {
        return new ResponseEntity<>(AddressMapper.INSTANCE.addressToDto(addressService.getByKey(id)), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<AddressDto> delete(@PathVariable("id") Long id) {

        addressService.getByKey(id).getUser().setAddress(null);
        addressService.deleteByKeyCascadeEnable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
