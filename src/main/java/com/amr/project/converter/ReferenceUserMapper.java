package com.amr.project.converter;

import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Component;

@Component
public class ReferenceUserMapper {
    final
    UserService userService;

    public ReferenceUserMapper(UserService userService) {
        this.userService = userService;
    }

    public User map(String username) {
        return userService.getByName(username);
    }
}
