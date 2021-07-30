package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.RoleDao;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.RoleService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RoleServiceImpl extends ReadWriteServiceImpl<Role, Long> implements RoleService {

}
