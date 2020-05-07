package com.modus.create.gateway.service;

import com.modus.create.users.command.CreateUser;
import com.modus.create.users.command.CreatedUser;
import com.modus.create.users.command.GetToken;
import com.modus.create.users.command.Token;

public interface AuthService {
    CreatedUser register(CreateUser user);
    Token login(GetToken getToken);
}
