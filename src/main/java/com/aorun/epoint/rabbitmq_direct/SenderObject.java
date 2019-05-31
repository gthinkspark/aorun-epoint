package com.aorun.epoint.rabbitmq_direct;

import com.aorun.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderObject {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    //发送者
    public void sendObject(User user) {
        System.out.println("Sender object: " + user.toString());
        this.rabbitTemplate.convertAndSend(RabbitConfig.integralObjectQueue, user);
    }

}