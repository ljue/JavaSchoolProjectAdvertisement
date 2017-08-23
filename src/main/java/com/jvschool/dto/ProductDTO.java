package com.jvschool.dto;



import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;

@Getter
@Setter
public class ProductDTO  implements Serializable {

    private long productId;
    private String productName;
    private String presentProductName;
    private double cost;
    private byte[] picture;


    public void setPicture(String picture) {
        this.picture = DatatypeConverter.parseBase64Binary(picture);
    }

    public void setProductName(String productName) {
        this.productName = productName;
        this.presentProductName = this.productName.substring(0, this.productName.substring(0,15).lastIndexOf(" ")) + "...";
    }
}
