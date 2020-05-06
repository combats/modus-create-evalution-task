package com.modus.create.gateway.service.impl;

import com.modus.create.gateway.service.TokenParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenParserImpl implements TokenParser {

    @Value("${com.modus.create.users.access_token.secret}")
    private String secret;

    @Override
    public UUID parseAccessToken(String accessToken) {
        Claims body = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(accessToken)
                .getBody();

        UUID userDetailsId = UUID.fromString(body.get("id", String.class));
        return userDetailsId;
    }
}
