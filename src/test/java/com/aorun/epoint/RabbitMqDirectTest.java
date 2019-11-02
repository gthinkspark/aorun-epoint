package com.aorun.epoint;

import com.aorun.epoint.rabbitmq_direct.HelloSender1;
import com.aorun.epoint.rabbitmq_direct.HelloSender2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class RabbitMqDirectTest extends AorunEpointApplicationTests{

    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender2 helloSender2;

//    @Autowired
//    private SenderObject senderObject;

    @Test
    public void hello() throws Exception {
        helloSender1.send();
    }


    @Test
    public void oneToMany() throws Exception {
        for (int i = 0; i < 50; i++) {
            helloSender1.send1(i);
        }
    }

    @Test
    public void manyToMany() throws Exception {
        for (int i = 0; i < 50; i++) {
            helloSender1.send1(i);
            helloSender2.send2(i);
        }
    }


//    @Test
//    public void sendUser() throws Exception {
//        User user = new User();
//        user.setName("aaaa");
//        user.setPass("bbbb");
//        senderObject.sendObject(user);
//    }

}