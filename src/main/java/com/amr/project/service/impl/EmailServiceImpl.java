package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.AddressDao;
import com.amr.project.dao.abstracts.EmailDao;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.Mail;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.AddressService;
import com.amr.project.service.abstracts.EmailService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
@Transactional
public class EmailServiceImpl extends ReadWriteServiceImpl<User, Long> implements EmailService {

    private final UserDao userDao;
    private final UserService userService;
    private final AddressService addressService;
    private final AddressDao addressDao;
    private final EmailSender emailSender;
    private final EmailDao emailDao;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public EmailServiceImpl(UserDao userDao, EmailSender emailSender, PasswordEncoder passwordEncoder,
                            UserService userService, EmailDao emailDao, AddressService addressService, AddressDao addressDao) {
        super(emailDao);
        this.emailDao = emailDao;
        this.userDao = userDao;
        this.emailSender = emailSender;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.addressService = addressService;
        this.addressDao = addressDao;
    }

    @Override
    public void notifyAboutOrderStatus(User user) {

        Collection<Order> orders = user.getOrders();
        ArrayList<Order> allOrders = new ArrayList<>(orders);

        for (int i = 0; i < allOrders.size(); i++) {
            Order order = allOrders.get(i);
            Mail mail = Mail.statusOrder(order);
            emailSender.send(mail.getSubject(), mail.getTo(), mail.getMessage());
        }
    }

    @Override
    public void updateUserProfile(User originalUser, User changeUser) {
        if (!Objects.equals(originalUser.getAddress(), changeUser.getAddress())) {
            Mail mailAboutAddressChange = Mail.changeUser(originalUser, changeUser, changeUser.getAddress());
            emailSender.send(mailAboutAddressChange.getSubject(), mailAboutAddressChange.getTo(),
                    mailAboutAddressChange.getMessage());
            addressService.persist(changeUser.getAddress());
            addressDao.update(changeUser.getAddress());
        } else {
            Mail mailAboutUserDataChange = Mail.changeUser(originalUser, changeUser);
            emailSender.send(mailAboutUserDataChange.getSubject(), mailAboutUserDataChange.getTo(),
                    mailAboutUserDataChange.getMessage());
            userService.update(changeUser);
            userDao.update(changeUser);
        }
    }

    @Override
    public void updatePassword(User originalUser, User changeUser) {
        if (!Objects.equals(originalUser.getPassword(), changeUser.getPassword())) {
            Mail mail = Mail.changePassword(originalUser.getEmail(), originalUser.getFirstName());
            emailSender.send(mail.getSubject(), mail.getTo(), mail.getMessage());
            changeUser.setPassword(passwordEncoder.encode(changeUser.getPassword()));
            userService.update(changeUser);
            userDao.update(changeUser);
        }
    }

    @Override
    public void notifyAboutShopRegistration(Shop shop) {
        Mail mail = Mail.createShop(shop);
        emailSender.send(mail.getSubject(), mail.getTo(), mail.getMessage());
    }

    @Override
    public void notifyShopApproval(Shop shop) {
        Mail mail = Mail.isModerateShop(shop);
        emailSender.send(mail.getSubject(), mail.getTo(), mail.getMessage());
    }

    @Override
    public boolean addUser(User user) {

        user.setActivate(true);
        user.setActivationCode(Mail.generateActivationCode());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.persist(user);

//        Отвалилась функция внезапно (в ней ничего не менял), скорее всего владелец почты поменял учетку.
//        notifyAboutUserRegistration(user);
        return true;
    }


    public void notifyAboutUserRegistration(User user) {
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
