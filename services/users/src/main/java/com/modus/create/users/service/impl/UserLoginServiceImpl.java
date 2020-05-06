package com.modus.create.users.service.impl;

import com.modus.create.users.command.GetToken;
import com.modus.create.users.command.Token;
import com.modus.create.users.dao.UserDao;
import com.modus.create.users.entity.UserAuth;
import com.modus.create.users.service.UserLoginService;
import com.modus.create.users.utils.TimeUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private static final int HOURS_TO_MILLISECONDS = 60 * 60 * 1000;

    @Value("${com.modus.create.users.access_token.secret}")
    private String secret;
    @Value("${com.modus.create.users.access_token.ttl.hours}")
    private int accessTokenTtlInSeconds;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TimeUtils timeUtils;
    @Autowired
    private UserDao userDao;

    @Override
    public Token login(GetToken getToken) {
        UserAuth user = userDao.findByLogin(getToken.getLogin());
        boolean isPasswordMatch = passwordEncoder.matches(getToken.getPassword(), user.getPassword());

        Claims claims = Jwts.claims();
        claims.put("id", user.getId());

        return new Token(
                Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(new Date(timeUtils.nowLong() + accessTokenTtlInSeconds * HOURS_TO_MILLISECONDS))
                        .signWith(HS256, secret.getBytes())
                        .compact()
        );
    }
}
