package com.amr.project.service.abstracts;

import com.amr.project.model.entity.User;

import java.util.Optional;

public interface UserService extends ReadWriteService<User, Long> {
    public Optional<User> findByUsername(String username);
}
