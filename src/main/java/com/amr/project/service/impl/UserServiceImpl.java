package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.UserDAO;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUser(long id) {
        return userDAO.findById(id).orElse(null);
    }

    @Override
    public void editUser(User user) {
        userDAO.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteById(id);
    }
}
