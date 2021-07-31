package com.amr.project.dao.abstracts;


import com.amr.project.model.entity.User;

import java.util.Optional;

public interface UserDao extends ReadWriteDAO<User, Long> {
    public Optional<User> findByUsername(String username);

}
