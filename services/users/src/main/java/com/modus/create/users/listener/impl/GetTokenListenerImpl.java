package com.modus.create.users.listener.impl;

import com.google.gson.Gson;
import com.modus.create.users.command.GetToken;
import com.modus.create.users.command.Token;
import com.modus.create.users.listener.GetTokenListener;
import com.modus.create.users.service.UserLoginService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class GetTokenListenerImpl implements GetTokenListener {

    private static final String QUEUE_NAME = "com.modus.create.get.token";
    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private Gson gson;
    @Autowired
    private UserLoginService userLoginService;

    @PostConstruct
    public void initQueue() {
        rabbitAdmin.declareQueue(new Queue(QUEUE_NAME));
    }

    @Override
    public String receive(String message) {
        GetToken getToken = gson.fromJson(message, GetToken.class);
        Token token = userLoginService.login(getToken);
        return gson.toJson(token);
    }
}
