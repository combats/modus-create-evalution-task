package com.modus.create.gateway.controller;

import com.modus.create.gateway.security.AuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class SecuredController {

    @Autowired
    private AuthContext authContext;

    @RequestMapping(method = GET, value = "/secured")
    public String register() {
        return authContext.getCurrentUserId().toString();
    }
}
