package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.dao.abstracts.UserDAO;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<User, Long> implements UserService{

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        super(userDAO);
        this.userDAO = userDAO;
    }

}
