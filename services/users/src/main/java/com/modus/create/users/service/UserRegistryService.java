package com.modus.create.users.service;

import com.modus.create.users.command.CreateUser;
import com.modus.create.users.command.CreatedUser;

public interface UserRegistryService {

    CreatedUser register(CreateUser createUser);
}
