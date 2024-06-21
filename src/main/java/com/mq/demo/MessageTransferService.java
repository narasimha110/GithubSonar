package com.mq.demo;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  
public class MessageTransferService {

	@Autowired
    private ConnectionFactory sourceConnectionFactory;

    @Autowired
    private ConnectionFactory destinationConnectionFactory;

    @Autowired
    private Queue sourceQueue;

    @Autowired
    private Queue destinationQueue;
    
    public void transferMessages() {
        try (JMSContext sourceContext = sourceConnectionFactory.createContext();
             JMSContext destinationContext = destinationConnectionFactory.createContext()) {
            String message = sourceContext.createConsumer(sourceQueue).receiveBody(String.class);
            destinationContext.createProducer().send(destinationQueue, message);
            System.out.println("Message transferred successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
}
