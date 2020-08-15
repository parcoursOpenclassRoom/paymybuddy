package com.maymybuddy.paymybuddy.Entity;

import java.util.Date;

public class Transaction {
    private int id;
    private double amount;
    private String type;
    private String description;
    private Date created;
    private Date modified;

    private Account account;

    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
