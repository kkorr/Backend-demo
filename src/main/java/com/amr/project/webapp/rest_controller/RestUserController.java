package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.RoleService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(("/api"))
public class RestUserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestUserController(UserService userService,
                              RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/save")
    public User registerUser(@RequestBody UserDto userDto) {

        User user = UserMapper.INSTANCE.dtoToUser(userDto);
        if(user != null) {
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
}
