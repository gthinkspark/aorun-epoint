package com.aorun.epoint.rabbitmq_direct.receiver;

import com.aorun.User;
import com.aorun.epoint.rabbitmq_direct.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.integralObjectQueue)
public class ReceiverObject {

    @RabbitHandler
    public void process(User user) {
        System.out.println("ReceiverObject  : " + user.toString());
    }

}