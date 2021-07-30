package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.RoleDao;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDAOImpl extends ReadWriteDAOImpl<Role, Long> implements RoleDao {
}
