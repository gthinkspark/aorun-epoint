package com.aorun.epoint;

import com.aorun.EpointMsgDataStructure;
import com.aorun.epoint.rabbitmq_direct.SenderEpointMsgDataStructure;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


public class RabbitMqEpointMsgDataStructureTest extends AorunEpointApplicationTests{

    @Autowired
    private SenderEpointMsgDataStructure senderEpointMsgDataStructure;

    @Test
    public void sendEpointMsgDataStructure() throws Exception {
        EpointMsgDataStructure epointMsgDataStructure = new EpointMsgDataStructure();

        epointMsgDataStructure.setBizUniqueSignCode("zz");
        epointMsgDataStructure.setEpoint(0);
        epointMsgDataStructure.setEpointConfigCode("TASK_9");
        epointMsgDataStructure.setMsgId(UUID.randomUUID().toString());
        epointMsgDataStructure.setWorkerId(3L);

        System.out.println("epointMsgDataStructure:"+epointMsgDataStructure.toString());
        senderEpointMsgDataStructure.sendObject(epointMsgDataStructure);
    }

}