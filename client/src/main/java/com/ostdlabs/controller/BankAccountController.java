package com.ostdlabs.controller;

import com.ostdlabs.JmsService;
import com.ostdlabs.model.BankAccount;
import com.ostdlabs.producer.JmsMessageProducer;
import com.ostdlabs.service.BankAccountService;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Yoghurt_92 on 21.11.2015.
 */

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    private BrokerService broker;

    @Autowired
    private JmsService jmsService;

    @Autowired
    private BankAccountService service;

    @Autowired
    private JmsMessageProducer jmsMessageProducer;

    private static final String URI = "broker:(tcp://localhost:61616)";

    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Exception handleConflict(Exception ex) {
        return ex;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void sendList(@RequestBody List<BankAccount> accounts) throws Exception {
        if (broker == null) {
            broker = BrokerFactory.createBroker(new URI(URI));
        }
        broker.start();

        jmsMessageProducer.sendMessage(accounts.toString());
        jmsService.writeLog(broker);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<BankAccount> getAccountList() {
        return service.findAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    BankAccount getAccountById(@PathVariable ("id") Integer id) {
        return service.findOne(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    BankAccount saveAccount(@RequestBody BankAccount account) {
        return service.save(account);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    BankAccount updateAccount(@RequestBody BankAccount account) {
        return service.update(account);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public
    @ResponseStatus(HttpStatus.OK)
    void deleteAccount(@RequestBody BankAccount account) {
        service.delete(account);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public
    @ResponseStatus(HttpStatus.OK)
    void deleteAccountById(@PathVariable ("id") Integer id) {
        service.delete(id);
    }
}
