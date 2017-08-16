package com.jvschool.util;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Receiver {

    public static void receive() throws NamingException, JMSException {

        Hashtable<String, String> props = new Hashtable<>();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", "tcp://localhost:61616");
        props.put("queue.js-queue", "my_jms_queue");
        props.put("connectionFactoryNames", "queueCF");

        Context context = new InitialContext(props);
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("queueCF");
        Queue queue = (Queue) context.lookup("js-queue");

        QueueConnection connection = connectionFactory.createQueueConnection();
        connection.start();

        QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

        QueueReceiver receiver = session.createReceiver(queue);

        receiver.setMessageListener(new MyMessageListener());

        TextMessage message = (TextMessage) receiver.receive();

        System.out.println("message received: " + message.getText());

        receiver.close();
        session.close();
        connection.close();

    }
}
