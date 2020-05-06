package com.modus.create.users.service.impl;

import com.modus.create.users.command.CreateUser;
import com.modus.create.users.command.CreatedUser;
import com.modus.create.users.dao.UserDao;
import com.modus.create.users.entity.UserAuth;
import com.modus.create.users.service.UserRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistryServiceImpl implements UserRegistryService {

    private PasswordEncoder passwordEncoder;
    private UserDao userDao;

    @Autowired
    public UserRegistryServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @Override
    public CreatedUser register(CreateUser createUser) {
        UserAuth user = UserAuth.builder()
                .login(createUser.getLogin())
                .password(passwordEncoder.encode(createUser.getPassword())).build();
        UserAuth savedUser = userDao.save(user);
        return new CreatedUser(savedUser.getId());
    }
}
