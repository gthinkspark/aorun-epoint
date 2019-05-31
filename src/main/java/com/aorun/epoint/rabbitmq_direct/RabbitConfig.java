package com.aorun.epoint.rabbitmq_direct;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    public static final String integralQueue = "integralQueue";

    public static final String integralObjectQueue = "integralObjectQueue";

    public static final String epointMsgDataStructureQueue = "epointMsgDataStructureQueue";


    //积分队列
    @Bean
    public Queue integralQueue() {
        return new Queue(RabbitConfig.integralQueue);
    }

    //积分对象队列
    @Bean
    public Queue integralObjectQueue() {
        return new Queue(RabbitConfig.integralObjectQueue);
    }

    //积分对象队列
    @Bean
    public Queue epointMsgDataStructureQueue() {
        return new Queue(RabbitConfig.epointMsgDataStructureQueue);
    }

}