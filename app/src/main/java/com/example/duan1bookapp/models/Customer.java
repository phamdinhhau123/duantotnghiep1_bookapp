package com.example.duan1bookapp.models;

import java.util.Date;

public class Customer {
    private int id;
    private String customerName;
    private String customerPassword;
    private String customereMail;
    private String customerbirthDate;

    public Customer() {
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomereMail() {
        return customereMail;
    }

    public void setCustomereMail(String customereMail) {
        this.customereMail = customereMail;
    }

    public String getCustomerbirthDate() {
        return customerbirthDate;
    }

    public boolean isEnabled_CS() {
        return enabled_CS;
    }

    public void setEnabled_CS(boolean enabled_CS) {
        this.enabled_CS = enabled_CS;
    }

    public void setCustomerbirthDate(String customerbirthDate) {
        this.customerbirthDate = customerbirthDate;
    }

    public Customer(String customerName, String customerPassword, String customereMail, String customerbirthDate, boolean enabled_CS) {
        this.customerName = customerName;
        this.customerPassword = customerPassword;
        this.customereMail = customereMail;
        this.customerbirthDate = customerbirthDate;
        this.enabled_CS = enabled_CS;
    }

    private boolean enabled_CS;
}
