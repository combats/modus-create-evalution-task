package com.modus.create.gateway.controller;

import com.modus.create.gateway.service.AuthService;
import com.modus.create.users.command.CreateUser;
import com.modus.create.users.command.CreatedUser;
import com.modus.create.users.command.GetToken;
import com.modus.create.users.command.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(method = POST, value = "/auth/register")
    public CreatedUser register(@RequestBody CreateUser user) {
        return authService.register(user);
    }

    @RequestMapping(method = POST, value = "/auth/login")
    public Token login(@RequestBody GetToken getToken) {
        return authService.login(getToken);
    }
}
