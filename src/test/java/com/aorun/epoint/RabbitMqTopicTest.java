package com.aorun.epoint;

import com.aorun.epoint.rabbitmq_topic.TopicSender;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;


public class RabbitMqTopicTest extends AorunEpointApplicationTests{

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Test
    public void topicSender() {
        topicSender.send();
    }

    @Test
    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", context);
    }

    @Test
    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
    }


}