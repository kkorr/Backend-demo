package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (userDao.findByUsername(s).isPresent()) {
            UserDetails userDetails = userDao.findByUsername(s).get().getUserDetails();
            LOGGER.info("Login this user: " + userDetails.getUsername());
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found");
    }
}
