package com.jvschool.util;

import lombok.extern.log4j.Log4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@Log4j
@Startup
@Singleton
public class Receiver {

    QueueConnection connection;
    QueueSession session;
    QueueReceiver receiver;

    @PostConstruct
    public void receive() throws NamingException, JMSException {

        Hashtable<String, String> props = new Hashtable<>();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", "tcp://localhost:61616");
        props.put("queue.js-queue", "my_jms_queue");
        props.put("connectionFactoryNames", "queueCF");

        Context context = new InitialContext(props);
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("queueCF");
        Queue queue = (Queue) context.lookup("js-queue");

        connection = connectionFactory.createQueueConnection();
        connection.start();

        session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

        receiver = session.createReceiver(queue);

        receiver.setMessageListener(new MyMessageListener());

    }

    @PreDestroy
    public void destroyReceiver() {

        try {
            receiver.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            log.info(e.toString());
            //e.printStackTrace();
        }

    }
}
