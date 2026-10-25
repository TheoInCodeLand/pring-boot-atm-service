package com.capitec.atm;

import java.util.ArrayList;

public class Account {
    private String accountNumber;
    private String ownerName;
    private double balance;

    private ArrayList<String> transactions = new ArrayList<>();

    public Account(){
        // Empty Constructor (Required by Spring/Jackson & Databases)
    };

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.transactions.add("Account opened with initial balance: R" + balance + "\n\n");
    }

    boolean withdraw(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Transaction Failed. Invalid Amount.");
        }
        if (amount <= this.balance){
            this.balance -= amount;
            this.transactions.add("Withdrew: -R" + amount + " | New Balance: R" + this.balance);

            return true;

        } else {
            return false;

        }
    }

    public void deposit(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Deposit Failed. Invalid Amount.");
        }
        this.balance += amount;
        this.transactions.add("Deposited: +R" + amount + " | New Balance: R" + this.balance);

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

}