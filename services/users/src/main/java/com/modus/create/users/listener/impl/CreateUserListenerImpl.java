package com.modus.create.users.listener.impl;

import com.google.gson.Gson;
import com.modus.create.users.command.CreateUser;
import com.modus.create.users.command.CreatedUser;
import com.modus.create.users.listener.CreateUserListener;
import com.modus.create.users.service.UserRegistryService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CreateUserListenerImpl implements CreateUserListener {

    private static final String QUEUE_NAME = "com.modus.create.create.user";

    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private Gson gson;
    @Autowired
    private UserRegistryService userRegistryService;

    @PostConstruct
    public void initQueue() {
        rabbitAdmin.declareQueue(new Queue(QUEUE_NAME));
    }

    @Override
    @RabbitListener(queues = QUEUE_NAME)
    public String receive(String message) {
        CreateUser createUser = gson.fromJson(message, CreateUser.class);
        CreatedUser createdUser = userRegistryService.register(createUser);
        return gson.toJson(createdUser);
    }
}
