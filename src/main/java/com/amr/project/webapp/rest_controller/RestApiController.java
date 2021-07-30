package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.RoleService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController("/api")
public class RestApiController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestApiController(UserService userService,
                             RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/save")
    public User registerUser(@RequestBody UserDto userDto) {

        //TODO как можно сравнивать имя пользователя и Optional объект? у тебя всегда будет false
        if (userDto.getUsername().equals(userService.findByUsername(userDto.getUsername()))) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }

        //TODO проверь постманом чтобы мапились все поля юзера
        var user = UserMapper.INSTANCE.dtoToUser(userDto);
        //TODO это хрень, мы не знаем под каким id будет юзер в БД, нужно искать по имени!
        var role = roleService.getByKey(1L);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.persist(user);
        return user;
    }
}
