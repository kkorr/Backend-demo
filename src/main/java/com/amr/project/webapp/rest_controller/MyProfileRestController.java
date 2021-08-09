package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CityService;
import com.amr.project.service.abstracts.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class MyProfileRestController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final CityService cityService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MyProfileRestController(UserService userService, UserMapper userMapper, CityService cityService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        if(userService.existsById(id)) {
            UserDto userDto = userMapper.userToDto(userService.getByKey(id));
            return ResponseEntity.ok().body(userDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User with ID: %d does not exist", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (Objects.equals(id, userDto.getId())) {
            User user = userMapper.dtoToUser(userDto);
            if (userService.existsById(userDto.getId())) {
                cityService.persist(user.getAddress().getCity());
                userService.update(user);
                logger.info(String.format("user с ID: %d updated successfully", userDto.getId()));
                return ResponseEntity.ok().body(userMapper.userToDto(user));
            }
            logger.info(String.format("Пользователь с ID: %d не существует", userDto.getId()));
        }
        logger.info(String.format("Указанный ID: %d не совпадает с ID пользователя %d", id, userDto.getId()));
        return ResponseEntity.badRequest().body(String.format("Specified ID: %d does not match user ID: %d", id, userDto.getId()));
    }
}
