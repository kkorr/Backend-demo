package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;

public interface EmailService extends ReadWriteService<User, Long> {

    boolean addUser(User user);

    boolean activateUser(String code);

    void updateUserProfile(User originalUser, User changeUser);

    void updatePassword(User originalUser, User changeUser);

    void notifyAboutOrderStatus(User user);

    void notifyAboutShopRegistration(Shop shop);

    void notifyShopApproval(Shop shop);
}
