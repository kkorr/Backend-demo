package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.RoleDao;
import com.amr.project.model.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl extends ReadWriteDAOImpl<Role, Long> implements RoleDao {
}
