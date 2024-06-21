package com.mq.demo;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jms.connection.CachingConnectionFactory;

import com.ibm.mq.jms.MQConnectionFactory;

@Configuration
public class MQConfig {
	
	 @Value("${source.mq.host}")
	    private String sourceHost;

	    @Value("${source.mq.port}")
	    private int sourcePort;

	    @Value("${source.mq.queueManager}")
	    private String sourceQueueManager;

	    @Value("${source.mq.channel}")
	    private String sourceChannel;

	    @Value("${source.mq.queue}")
	    private String sourceQueueName;

	    @Value("${destination.mq.host}")
	    private String destinationHost;

	    @Value("${destination.mq.port}")
	    private int destinationPort;

	    @Value("${destination.mq.queueManager}")
	    private String destinationQueueManager;

	    @Value("${destination.mq.channel}")
	    private String destinationChannel;

	    @Value("${destination.mq.queue}")
	    private String destinationQueueName;
	    
	    @Bean
	    public ConnectionFactory sourceConnectionFactory() throws JMSException {
	        MQConnectionFactory mqConnectionFactory = new MQConnectionFactory();
	        mqConnectionFactory.setHostName(sourceHost);
	        mqConnectionFactory.setPort(sourcePort);
	        mqConnectionFactory.setQueueManager(sourceQueueManager);
	        mqConnectionFactory.setChannel(sourceChannel);
	        return new CachingConnectionFactory(mqConnectionFactory);
	    }
	    
	    @Bean
	    public ConnectionFactory destinationConnectionFactory() throws JMSException {
	        MQConnectionFactory mqConnectionFactory = new MQConnectionFactory();
	        mqConnectionFactory.setHostName(destinationHost);
	        mqConnectionFactory.setPort(destinationPort);
	        mqConnectionFactory.setQueueManager(destinationQueueManager);
	        mqConnectionFactory.setChannel(destinationChannel);
	        return new CachingConnectionFactory(mqConnectionFactory);
	    }
	    
	    @Bean
	    public Queue sourceQueue() throws JMSException {
	        return new com.ibm.mq.jms.MQQueue(sourceQueueName);
	    }

	    @Bean
	    public Queue destinationQueue() throws JMSException {
	        return new com.ibm.mq.jms.MQQueue(destinationQueueName);
	    }

	    @Bean
	    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	        return new PropertySourcesPlaceholderConfigurer();
	    }  
	
}
