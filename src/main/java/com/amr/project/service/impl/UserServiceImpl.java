package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ReadWriteService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserServiceImpl extends ReadWriteServiceImpl<User, Long> implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    public Optional<User> findByUsername(String username) {
        return  userDao.findByUsername(username);
    };
}
