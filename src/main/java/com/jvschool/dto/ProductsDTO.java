package com.jvschool.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.Serializable;
import java.util.List;

@Log4j
@Getter
@Setter
@Singleton
public class ProductsDTO  implements Serializable {

    private List<ProductDTO> products;

    @PostConstruct
    public void onStart() {
        log.info("DTO list construct");
    }

}
