package com.modus.create.users.dao;

import com.modus.create.users.entity.UserAuth;

public interface UserDao {
    UserAuth save(UserAuth user);
    UserAuth findByLogin(String login);
}
