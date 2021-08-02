package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends ReadWriteDAO<User, Long> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}