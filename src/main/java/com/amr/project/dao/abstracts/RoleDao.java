package com.amr.project.dao.abstracts;


import com.amr.project.model.entity.Role;

import java.util.Optional;

public interface RoleDao extends ReadWriteDAO<Role, Long> {

    public Role findByName(String roleName);
}
