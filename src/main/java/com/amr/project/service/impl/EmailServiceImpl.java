package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.EmailDao;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.Mail;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.EmailService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmailServiceImpl extends ReadWriteServiceImpl<User, Long> implements EmailService {

    private final UserDao userDao;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final EmailDao emailDao;


    @Autowired
    public EmailServiceImpl(UserDao userDao, EmailSender emailSender, PasswordEncoder passwordEncoder,
                            UserService userService, EmailDao emailDao) {
        super(emailDao);
        this.emailDao = emailDao;
        this.userDao = userDao;
        this.emailSender = emailSender;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public boolean addUser(User user) {

        user.setActivate(true);
        user.setActivationCode(Mail.generateActivationCode());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.persist(user);

        toNotifyAboutRegistration(user);
        return true;
    }

    public void toNotifyAboutRegistration(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            Mail mail = Mail.activationMail(user);
            emailSender.send(mail.getSubject(), mail.getTo(), mail.getMessage());
        }
    }

    @Override
    public boolean activateUser(String code) {
        User user = emailDao.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userDao.persist(user);
        return true;
    }
}
