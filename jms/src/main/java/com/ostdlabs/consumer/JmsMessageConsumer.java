package com.ostdlabs.consumer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Yoghurt_92 on 22.11.2015.
 */

@Service
public class JmsMessageConsumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination destination;

    public String receiveMessage() throws JMSException {
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
        return textMessage.getText();
    }
}
