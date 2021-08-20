package com.amr.project.service.abstracts;

import com.amr.project.model.entity.User;

public interface EmailService extends ReadWriteService<User, Long> {

    boolean addUser(User user);

    boolean activateUser(String code);
}
