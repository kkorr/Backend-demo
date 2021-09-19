package com.amr.project.service.abstracts;

import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;

import java.util.Optional;

public interface UserService extends ReadWriteService<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    void updateUserOnUserPage(User user, UserDto userDto);
}
