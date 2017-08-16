package com.jvschool.dto;

import java.io.Serializable;
import java.util.List;

public class ProductsDTO  implements Serializable {
    List<ProductDTO> products;


    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
