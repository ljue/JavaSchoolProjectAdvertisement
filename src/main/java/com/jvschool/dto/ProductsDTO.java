package com.jvschool.dto;

import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ProductsDTO  implements Serializable {

    List<ProductDTO> products;


    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
