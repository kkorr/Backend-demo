package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.*;
import com.amr.project.service.abstracts.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final ShopMapper shopMapper;
    private final EmailService emailService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserRestController(UserService userService, RoleService roleService,
                              UserMapper userMapper, CountryService countryService, CityService cityService,
                              EmailService emailService, ShopMapper shopMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.shopMapper = shopMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getAddress(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userMapper.userToDto(userService.getByKey(id)), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto, Model model) {

        User user = userMapper.dtoToUser(userDto);
        if (user != null) {
            if (userService.findByUsername(user.getUsername()).isPresent()) {
                model.addAttribute("usernameExists", "Пользователь с таким username уже существует");
                LOGGER.info(String.format("Пользователь с таким юзернеймом %s уже существует", user.getUsername()));
                throw new IllegalArgumentException("Пользователь с таким именем уже существует");

            }
            if (userService.findByEmail(user.getEmail()).isPresent()) {
                model.addAttribute("emailExists", "Пользователь с такой электронной почтой уже существует");
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
        } catch (Exception ignored) {
        }
        try {
            City city = cityService.getByName(userDto.getAddress().getCity());
            user.getAddress().setCity(city);
        } catch (Exception ignored) {
        }

        emailService.addUser(user);

        LOGGER.info(String.format("Пользователь с id %d успешно зарегистрирован", user.getId()));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/save-user")
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
        if (!userDto.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            userDto.getRoles().stream().forEach(x -> roles.add(roleService.findByName(x.getName())));
            userDto.setRoles(roles);
        }

        User user = userMapper.dtoToUser(userDto);
        user.setAge(user.calculateAge());
        userService.update(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        Set<Role> roles = new HashSet<>();
        userDto.getRoles().stream().forEach(x -> roles.add(roleService.findByName(x.getName())));
        userDto.setRoles(roles);

        User user = userMapper.dtoToUser(userDto);
        user.setAge(user.calculateAge());
        userService.persist(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.deleteByKeyCascadeEnable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
