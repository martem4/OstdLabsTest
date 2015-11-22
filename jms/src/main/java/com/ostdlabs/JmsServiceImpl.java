package com.ostdlabs;

import com.ostdlabs.consumer.JmsMessageConsumer;
import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

/**
 * Created by Yoghurt_92 on 22.11.2015.
 */
@Service
public class JmsServiceImpl implements JmsService {

    final static Logger logger = Logger.getLogger(JmsServiceImpl.class);

    @Autowired
    private JmsMessageConsumer jmsMessageConsumer;

    @Override
    public void writeLog(BrokerService broker) {
        try {
            logger.debug("Write list accounts to log file: " + jmsMessageConsumer.receiveMessage());
        } catch (JMSException exc) {
            try {
                broker.stop();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
