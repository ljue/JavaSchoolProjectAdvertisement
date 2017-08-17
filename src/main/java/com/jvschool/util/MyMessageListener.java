package com.jvschool.util;

import com.jvschool.dto.ProductsDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import lombok.extern.log4j.Log4j;

import javax.ejb.EJB;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.ws.rs.core.MediaType;

@Log4j
public class MyMessageListener implements MessageListener {

    @EJB
    private ProductsDTO productsDTO;

    @Override
    public void onMessage(Message message) {

        log.info("Message received");

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource("http://localhost:8080/my-webapp/advertisement/top");

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        productsDTO = response.getEntity(ProductsDTO.class);
    }
}
