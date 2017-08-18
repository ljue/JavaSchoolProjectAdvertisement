package com.jvschool.dto;

import lombok.extern.log4j.Log4j;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.Serializable;
import java.util.List;

@Log4j
@Singleton
public class ProductsDTO  implements Serializable {

    private List<ProductDTO> products;


    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @PostConstruct
    public void onStart() {
        log.info("DTO list construct");
    }

}
