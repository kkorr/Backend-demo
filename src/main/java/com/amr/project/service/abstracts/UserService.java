package com.amr.project.service.abstracts;

import com.amr.project.model.entity.User;

public interface UserService {
    User getUser(long id);
    void editUser(User user);
    void deleteUser(long id);
}
