package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.User;

public interface EmailDao extends ReadWriteDAO<User, Long> {
    //Optional<User> findByActivationCode(String activationCode);

    User findByActivationCode(String activationCode);
}
