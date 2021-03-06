package com.joantolos.batch.si;

/**
 *
 * Created by joan on 24/1/15.
 */

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;

/**
 *
 * Created by jtolos on 13/01/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring-beans-test.xml"})
public class JmsMessageSenderTest {

    @Autowired
    JmsMessageSender jmsMessageSender;

    Queue testQueue;
    String testMessage;

    @Before
    public void setUp(){
        this.testQueue = new ActiveMQQueue("testQueue");
        this.testMessage = "Test Message";
    }

    @After
    public void tearDown(){
        this.jmsMessageSender = null;
        this.testQueue = null;

    }

    @Test
    public void sendDefaultQueueTest() {
        this.jmsMessageSender.send(this.testMessage);
    }

    public void sendTextTest() {
        this.jmsMessageSender.sendText(this.testMessage);
    }

    public void send() {
        this.jmsMessageSender.send(this.testQueue,this.testMessage);
    }
}
