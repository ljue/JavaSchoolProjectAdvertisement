package com.jvschool.dto;


import java.io.Serializable;

public class ProductDTO  implements Serializable {

    private long productId;
    private String productName;
    private String presentProductName;
    private double cost;
    private String description;
    private String category;


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPresentProductName() {
        return presentProductName;
    }

    public void setPresentProductName(String presentProductName) {
        this.presentProductName = presentProductName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
