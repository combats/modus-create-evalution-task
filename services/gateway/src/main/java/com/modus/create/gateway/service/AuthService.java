package com.modus.create.gateway.service;

import com.modus.create.gateway.command.CreateUser;
import com.modus.create.gateway.command.CreatedUser;
import com.modus.create.gateway.command.GetToken;
import com.modus.create.gateway.command.Token;

public interface AuthService {
    CreatedUser register(CreateUser user);
    Token login(GetToken getToken);
}
