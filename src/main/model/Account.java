package model;

import exceptions.ExceedMaxAmount;
import exceptions.NegativeAmount;
import exceptions.NegativeBalance;

public abstract class Account {
    protected double balance; // current balance of account
    protected String pin; // PIN number of account
    public static double STARTING_BALANCE = 0; // starting balance of account
    protected static double TRANSACTION_FEE = 0.25; // fee for every transaction performed
    public static double MAXIMUM_BALANCE = 100000; // each account can only hold up to $100,000


    // EFFECTS: account starts off with initial balance and a given PIN number
    public Account(String accountPin, Double initialBalance) {
        this.pin = accountPin;
        this.balance = initialBalance;
    }

    // MODIFIES: this
    // EFFECTS: amount is added to balance if it does not exceed the maximum allowed balance
    public boolean deposit(double amount) throws NegativeAmount, ExceedMaxAmount {
        if (amount < 0) {
            throw new NegativeAmount();
        } else if ((balance + amount) > MAXIMUM_BALANCE) {
            throw new ExceedMaxAmount();
        } else {
            balance = balance + amount - TRANSACTION_FEE;
            return true;
        }
    }


    // MODIFIES: this
    // EFFECTS: amount is taken from balance if there is sufficient balance (balance does not go negative)
    public boolean withdraw(double amount) throws NegativeBalance, NegativeAmount {
        if (amount < 0) {
            throw new NegativeAmount();
        } else if ((balance - amount) < 0) {
            throw new NegativeBalance();
        } else {
            balance = balance - amount - TRANSACTION_FEE;
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes account PIN to a new PIN
    public void changePin(String newpin) {
        pin = newpin;
    }

    // EFFECTS: returns PIN of account
    public String getPin() {
        return pin;
    }

    // EFFECTS: shows balance of account
    public double getBalance() {
        return balance;
    }

    public abstract String getType();

}
