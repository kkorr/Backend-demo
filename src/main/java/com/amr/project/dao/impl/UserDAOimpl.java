package com.amr.project.dao.impl;

import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAOImpl extends ReadWriteDAOImpl<User, Long> implements UserDao {
    @Override
    public Optional<User> findByUsername(String username) {
        return entityManager.createQuery("SELECT u from User u where u.username = :username", User.class)
                .setParameter("username", username).getResultList()
                .stream()
                .findAny();

    }
}
