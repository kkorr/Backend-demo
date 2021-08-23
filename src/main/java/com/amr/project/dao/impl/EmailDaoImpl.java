package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.EmailDao;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDaoImpl extends ReadWriteDAOImpl<User, Long> implements EmailDao {
//    @Override
//    public Optional<User> findByActivationCode(String activationCode) {
//        return entityManager.createQuery("SELECT u from User u where u.activationCode = :activationCode", User.class)
//                .setParameter("activationCode", activationCode).getResultList()
//                .stream()
//                .findAny();
//    }

    @Override
    public User findByActivationCode(String activationCode) {
        return entityManager.createQuery("SELECT u from User u where u.activationCode = :activationCode", User.class)
                .setParameter("activationCode", activationCode).getResultList()
                .stream()
                .findAny().get();
    }
}
