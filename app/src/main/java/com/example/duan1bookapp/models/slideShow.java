package com.example.duan1bookapp.models;

import java.io.Serializable;

public class slideShow implements Serializable {
    public int id;
    public String productNameS;
    public String getProductNameS;

    public slideShow(int id, String productNameS, String getProductNameS) {
        this.id = id;
        this.productNameS = productNameS;
        this.getProductNameS = getProductNameS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductNameS() {
        return productNameS;
    }

    public void setProductNameS(String productNameS) {
        this.productNameS = productNameS;
    }

    public String getGetProductNameS() {
        return getProductNameS;
    }

    public void setGetProductNameS(String getProductNameS) {
        this.getProductNameS = getProductNameS;
    }
}
