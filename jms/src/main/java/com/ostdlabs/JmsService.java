package com.ostdlabs;

import org.apache.activemq.broker.BrokerService;

/**
 * Created by Yoghurt_92 on 22.11.2015.
 */
public interface JmsService {

    void writeLog(BrokerService broker);
}
