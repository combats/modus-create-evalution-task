package com.modus.create.users;

import com.google.gson.Gson;
import com.modus.create.users.dao.UserDao;
import com.modus.create.users.dao.UserRdbDao;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Autowired
    private UserRdbDao userRdbDao;

    @Autowired
    private Gson gson;

    @Autowired
    private UserDao userDao;

    @Test
    @Ignore
    public void test() {
//        System.out.println(gson.toJson(new CreateUser("test_login_1", "test_password")));
//        UserAuth user = UserAuth.builder().login("test login").password("test password").build();
//        userDao.save(user);
//        System.out.println();
//
//        QueueInformation queueInfo = rabbitAdmin.getQueueInfo("com.modus.create.get.token");
//
//        QueueInformation queueInfo2 = rabbitAdmin.getQueueInfo("com.modus.create.create.user");


//        rabbitTemplate.convertAndSend("com.modus.create.get.token", "test name");
//        System.out.println();
//        rabbitTemplate.convertAndSend("com.modus.create.create.user", "test name");
//        System.out.println();

//          rabbitTemplate.convertAndSend("com.modus.test.queue", "test message");


    }

    @Test
    @Ignore
    void contextLoads() {
        QueueInformation queueInfo = rabbitAdmin.getQueueInfo("test.queue");
        rabbitAdmin.declareQueue(new Queue("test.queue"));


        rabbitTemplate.convertAndSend("test.queue", "test name");
        Message test_queue = rabbitTemplate.receive("test.queue");
        System.out.println();

        MessageProperties messageProperties = new MessageProperties();
        Message request = rabbitTemplate.getMessageConverter().toMessage("payload", messageProperties);

//        rabbitTemplate.receiveAndReply("test.queue", new ReceiveAndReplyMessageCallback() {
//            @Override
//            public Message handle(Message message) {
//                System.out.println("recieeve message" + message.toString());
//                Message response = rabbitTemplate.getMessageConverter().toMessage("echoed", new MessageProperties());
//                System.out.println("reply with message" + response.toString());
//                return response;
//            }
//        });

        Object message = rabbitTemplate.convertSendAndReceive("test.queue", "payload");

        System.out.println("handle replay message" + message.toString());
        System.out.println();
    }

}
