package com.amr.project.service.abstracts;

import com.amr.project.model.entity.User;

public interface UserService {
    User getUser();
    void editUser(User user);
    void deleteUser(long id);
}
