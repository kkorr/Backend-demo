package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.model.entity.Role;
import com.amr.project.service.abstracts.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ReadWriteServiceImpl<Role, Long> implements RoleService {

    public RoleServiceImpl(ReadWriteDAO<Role, Long> readWriteDAO) {
        super(readWriteDAO);
    }
}
