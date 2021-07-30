package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
}