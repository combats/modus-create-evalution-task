package com.modus.create.users.dao;

import com.modus.create.users.entity.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRdbDao implements UserDao {

    @Autowired
    private UserJpaDao userJpaDao;

    @Override
    public UserAuth save(UserAuth user) {
       return userJpaDao.save(user);
    }

    @Override
    public UserAuth findByLogin(String login) {
        return userJpaDao.findByLogin(login);
    }

    interface UserJpaDao extends JpaRepository<UserAuth, UUID> {

        UserAuth findByLogin(String login);
    }
}
