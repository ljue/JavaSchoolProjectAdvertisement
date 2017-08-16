package com.jvschool.view;


import com.jvschool.dto.ProductDTO;
import com.jvschool.dto.ProductsDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.io.Serializable;

@Named("controller")
@SessionScoped
public class AdvertisementController implements Serializable {


    //@EJB
//    private ProductDTO productDTO;
//
//    public String getProductDTOName() {
//        return productDTO.getPresentProductName();
//    }


    public ProductsDTO getProducts() {


        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource("http://localhost:8080/my-webapp/advertisement/top");

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        ProductsDTO productsDTO = response.getEntity(ProductsDTO.class);
        return productsDTO;
    }


}
