package com.modus.create.gateway.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthenticationToken extends AbstractAuthenticationToken {

    private final String token;

    public AuthenticationToken(String token) {
        super(null);
        this.token = token;
        setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    //todo investigate what must be return
    @Override
    public Object getCredentials() {
        return null;
    }
}