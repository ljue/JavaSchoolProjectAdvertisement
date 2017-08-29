package com.jvschool.util;

import com.jvschool.dto.ProductsDTO;
import com.jvschool.view.MyMessageListener;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import lombok.extern.log4j.Log4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.MediaType;
import java.util.Hashtable;

@Log4j
@Startup
@Singleton
public class Receiver {

    private QueueConnection connection;
    private QueueSession session;
    private QueueReceiver receiver;

    @EJB
    private ProductsDTO productsDTO;

    public Receiver() {
        log.info("receiver created");
    }

    @PostConstruct
    public void receive() {

        Hashtable<String, String> props = new Hashtable<>();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", "tcp://localhost:61616");
        props.put("queue.js-queue", "my_jms_queue");
        props.put("connectionFactoryNames", "queueCF");

        try {
            Context context = new InitialContext(props);
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("queueCF");
            Queue queue = (Queue) context.lookup("js-queue");

            connection = connectionFactory.createQueueConnection();
            connection.start();

            session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

            receiver = session.createReceiver(queue);

            receiver.setMessageListener(new MyMessageListener(this));
        } catch (NamingException e) {
            log.error(e.toString());
        } catch (JMSException e) {
            log.error(e.toString());
        }

        topReview();
    }

    public void topReview() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource("http://localhost:8080/my-webapp/advertisement/top");

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        log.info("top review");
        productsDTO = response.getEntity(ProductsDTO.class);
    }

    @PreDestroy
    public void destroyReceiver() {

        try {
            receiver.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            log.info(e.toString());
        }

    }

    public ProductsDTO getProductsDTO() {
        return productsDTO;
    }

    public void setProductsDTO(ProductsDTO productsDTO) {
        this.productsDTO = productsDTO;
    }

}
