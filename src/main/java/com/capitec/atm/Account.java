package com.capitec.atm;

public class Account {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public Account(){
        // Empty Constructor (Required by Spring/Jackson & Databases)
    };

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    boolean withdraw(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Transaction Failed. Invalid Amount.");
        }
        if (amount <= this.balance){
            this.balance -= amount;
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

}