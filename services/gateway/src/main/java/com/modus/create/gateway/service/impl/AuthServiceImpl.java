package com.modus.create.gateway.service.impl;

import com.google.gson.Gson;
import com.modus.create.gateway.command.CreateUser;
import com.modus.create.gateway.command.CreatedUser;
import com.modus.create.gateway.command.GetToken;
import com.modus.create.gateway.command.Token;
import com.modus.create.gateway.service.AuthService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String CREATE_USER_QUEUE_NAME = "com.modus.create.create.user";
    private static final String GET_TOKEN_QUEUE_NAME = "com.modus.create.get.token";


    @Autowired
    private Gson gson;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public CreatedUser register(CreateUser user) {
        String message = (String) rabbitTemplate.convertSendAndReceive(CREATE_USER_QUEUE_NAME, gson.toJson(user));
        return gson.fromJson(message, CreatedUser.class);
    }

    @Override
    public Token login(GetToken getToken) {
        String message = (String) rabbitTemplate.convertSendAndReceive(GET_TOKEN_QUEUE_NAME, gson.toJson(getToken));
        return gson.fromJson(message, Token.class);
    }
}
