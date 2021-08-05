package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.RoleDao;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl extends ReadWriteDAOImpl<Role, Long> implements RoleDao {

    public Role findByName(String roleName) {
        return entityManager.createQuery("SELECT r from Role r where r.name = :roleName", Role.class)
                .setParameter("roleName", roleName).getResultList()
                .stream()
                .findAny().get();
    }

}
