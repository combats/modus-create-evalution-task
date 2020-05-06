package com.modus.create.gateway.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthContext {

    public void setAuthentication(String userId) {
        SecurityContextHolder.getContext().setAuthentication(new AuthenticationToken(userId));
    }

    public UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        } else {
            String userId = (String) authentication.getPrincipal();
            return UUID.fromString(userId);
        }
    }
}