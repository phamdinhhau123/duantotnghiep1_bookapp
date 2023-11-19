package com.example.duan1bookapp.models;

import java.io.Serializable;

public class Coin implements Serializable {
    public int id;
    public int value;
    public int customer_id;

    public Coin() {
    }

    public Coin(int value, int customer_id) {
        this.value = value;
        this.customer_id = customer_id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "value=" + value +
                '}';
    }
}
