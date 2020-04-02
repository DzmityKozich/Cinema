package com.cinema.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BillingModel {

    private Long idBilling;
    private double balance;
    private UserModel billingUser;

    public BillingModel() {
    }

    public BillingModel(Long idBilling, double balance, UserModel billingUser) {
        this.idBilling = idBilling;
        this.balance = balance;
        this.billingUser = billingUser;
    }

    public Long getIdBilling() {
        return idBilling;
    }

    public void setIdBilling(Long idBilling) {
        this.idBilling = idBilling;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserModel getBillingUser() {
        return billingUser;
    }

    public void setBillingUser(UserModel billingUser) {
        this.billingUser = billingUser;
    }
}
