package com.maymybuddy.paymybuddy.Entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private int id;
    private double balance;
    private boolean defaultAccount = false;
    private Date created;
    private Date modified;
    private User user;
    private List<Transaction> transactions = new ArrayList<>();

    private List<Transfer> senderTransfer = new ArrayList<>();

    private List<Transfer> receiverTransfer = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transfer> getSenderTransfer() {
        return senderTransfer;
    }

    public void setSenderTransfer(List<Transfer> senderTransfer) {
        this.senderTransfer = senderTransfer;
    }

    public List<Transfer> getReceiverTransfer() {
        return receiverTransfer;
    }

    public void setReceiverTransfer(List<Transfer> receiverTransfer) {
        this.receiverTransfer = receiverTransfer;
    }

    public boolean isDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(boolean defaultAccount) {
        this.defaultAccount = defaultAccount;
    }
}
