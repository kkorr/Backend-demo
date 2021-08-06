package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CityService;
import com.amr.project.service.abstracts.CountryService;
import com.amr.project.service.abstracts.RoleService;
import com.amr.project.service.abstracts.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final CountryService countryService;
    private final CityService cityService;
    private final UserMapper userMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserRestController(UserService userService, RoleService roleService,
                              UserMapper userMapper, CountryService countryService, CityService cityService) {
        this.userService = userService;
        this.roleService = roleService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.userMapper = userMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {

        User user = userMapper.dtoToUser(userDto);
        if(user != null) {
            if (userService.findByUsername(user.getUsername()).isPresent()) {
                LOGGER.info(String.format("Пользователь с таким юзернеймом %s уже существует", user.getUsername()));
                throw new IllegalArgumentException("Пользователь с таким именем уже существует");
            }
            if (userService.findByEmail(user.getEmail()).isPresent()) {
                LOGGER.info(String.format("Пользователь с такой электронной почтой %s уже существует", user.getEmail()));
                throw new IllegalArgumentException("Пользователь с такой электронной почтой уже существует");
            }
        }
        Role role = roleService.findByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        try {
            Country country = countryService.getByName(userDto.getAddress().getCountry());
            user.getAddress().setCountry(country);
        } catch (Exception e) {}
        try {
            City city = cityService.getByName(userDto.getAddress().getCity());
            user.getAddress().setCity(city);
        } catch (Exception e) {}
        userService.persist(user);
        LOGGER.info(String.format("Пользователь с id %d успешно зарегистрирован", user.getId()));
        return ResponseEntity.ok().body(user);
    }
}
