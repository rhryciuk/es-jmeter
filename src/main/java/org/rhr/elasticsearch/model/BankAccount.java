package org.rhr.elasticsearch.model;

import java.math.BigDecimal;

public class BankAccount {

    private String accountNumber;

    private BigDecimal balance;

    private String owner;


    public BankAccount(String accountNumber, BigDecimal balance, String owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    private BankAccount() {
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", owner='" + owner + '\'' +
                '}';
    }
}
