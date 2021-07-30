package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.UserConverter;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/myprofile")
public class MyProfileRestController {

    private final UserService userService;
    private final UserConverter userConverter;

    public MyProfileRestController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getProfile(@PathVariable("id") long id) {
        UserDto userDto = userConverter.toDto(userService.getByKey(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<UserDto> editProfile(@RequestBody @Valid UserDto userDto) {
        User user = userConverter.toEntity(userDto);
        userService.update(user);
        UserDto userDtoAfterUpdate = userConverter.toDto(user);
        return new ResponseEntity<>(userDtoAfterUpdate, HttpStatus.OK);
    }
}
