package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserDAO;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOimpl extends ReadWriteDAOImpl<User, Long> implements UserDAO {
}
