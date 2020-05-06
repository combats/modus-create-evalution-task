package com.modus.create.users.service;

import com.modus.create.users.command.GetToken;
import com.modus.create.users.command.Token;

public interface UserLoginService {

    Token login(GetToken getToken);
}
