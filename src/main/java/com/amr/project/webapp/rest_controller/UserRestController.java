package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.RoleService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(("/api"))
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRestController(UserService userService,
                              RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/save")
    public User registerUser(@RequestBody UserDto userDto) {

        User user = UserMapper.INSTANCE.dtoToUser(userDto);
        if (user != null) {
            if (userService.findByUsername(user.getUsername()).isPresent() & userService.findByEmail(user.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Пользователь с таким именем уже существует");
            }
        }
        Role role = roleService.findByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.persist(user);
        return user;
    }

    @PutMapping("/save-user")
    public ResponseEntity<Long> saveUser(@RequestBody UserDto userDto) {
        Calendar calendar = Calendar.getInstance();

        if (!userDto.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            userDto.getRoles().stream().forEach(x -> roles.add(roleService.findByName(x.getName())));

            userDto.setRoles(roles);
        }

        User user = UserMapper.INSTANCE.dtoToUser(userDto);

        user.setAge(calendar.get(Calendar.YEAR) - user.getBirthday().get(1));

        userService.update(user);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addUser(@RequestBody UserDto userDto) {
        Calendar calendar = Calendar.getInstance();

        Set<Role> roles = new HashSet<>();
        userDto.getRoles().stream().forEach(x -> roles.add(roleService.findByName(x.getName())));
        userDto.setRoles(roles);

        User user = UserMapper.INSTANCE.dtoToUser(userDto);

        user.setAge(calendar.get(Calendar.YEAR) - user.getBirthday().get(1));

        userService.persist(user);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable("id") Long id) {
        userService.deleteByKeyCascadeEnable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
