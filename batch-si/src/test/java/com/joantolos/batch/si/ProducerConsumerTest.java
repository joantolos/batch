package com.joantolos.batch.si;

/**
 *
 * Created by jtolos on 24/1/15.
 */

import junit.framework.Assert;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 *
 * Created by jtolos on 13/01/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring-beans-test.xml"})
public class ProducerConsumerTest {

    @Autowired
    JmsMessageReceiver jmsMessageReceiver;

    @Autowired
    JmsMessageSender jmsMessageSender;

    Queue testQueue;

    @Before
    public void setUp(){
        this.testQueue = new ActiveMQQueue("testQueue");
    }

    @After
    public void tearDown(){
        this.jmsMessageReceiver = null;
        this.jmsMessageSender = null;
        this.testQueue = null;

    }

    @Test
    public void produceAndConsumeMessage() throws JMSException {
        jmsMessageSender.send(this.testQueue,"Producing test message...");

        TextMessage message = (TextMessage) jmsMessageReceiver.receive("testQueue");
        Assert.assertEquals(message.getText(), "Producing test message...");
    }

    @Test
    public void produceAndConsumeMultipleMessage() throws JMSException {
        for(int i=0; i <= 10; i++) {
            jmsMessageSender.send(this.testQueue, "Message number: " + i);
        }

        for(int i=0; i <= 10; i++) {
            TextMessage message = (TextMessage) jmsMessageReceiver.receive("testQueue");
            System.out.println(message.getText() + " received.");
            message.acknowledge();
        }
    }
}
