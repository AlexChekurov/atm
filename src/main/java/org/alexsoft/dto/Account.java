package org.alexsoft.dto;

import java.util.Date;


/**
 * Запись в файле данных с информацией о карте.
 */
public class Account {
    private String cardNumber;
    private String pinCode;
    private double balance;
    private boolean blocked;
    private Date blockDate;

    public Account(String cardNumber, String pinCode, double balance) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.balance = balance;
        this.blocked = false;
        this.blockDate = null;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void block() {
        this.blocked = true;
        this.blockDate = new Date();
    }

    public void unblock() {
        this.blocked = false;
        this.blockDate = null;
    }

    public Date getBlockDate() {
        return blockDate;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return cardNumber + " " + pinCode + " " + balance + " " + blocked + " " + (blockDate == null ? "null" : blockDate.getTime());
    }

    public static Account fromString(String data) {
        String[] parts = data.split(" ");
        Account account = new Account(parts[0], parts[1], Double.parseDouble(parts[2]));
        if (Boolean.parseBoolean(parts[3])) {
            account.block();
            account.blockDate = new Date(Long.parseLong(parts[4]));
        }
        return account;
    }
}

