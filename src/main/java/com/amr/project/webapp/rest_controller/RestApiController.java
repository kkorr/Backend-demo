package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.dao.impl.ReadWriteDAOImpl;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ReadWriteService;
import com.amr.project.service.abstracts.RoleService;
import com.amr.project.service.abstracts.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController("/api")
public class RestApiController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public RestApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User registerUser(@RequestBody UserDto userDto) {
        if(userDto.getUsername().equals(userService.findByUsername(userDto.getUsername()))) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }
        User user = UserMapper.INSTANCE.dtoToUser(userDto);
        // Вытащить роль Юзера с БД
        Role role = roleService.getByKey(1L);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.persist(user);
        return user;
    }
}
