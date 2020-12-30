package model;


import exceptions.ExceedMaxAmount;
import exceptions.NegativeAmount;
import exceptions.NegativeBalance;
import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static model.Account.MAXIMUM_BALANCE;
import static model.Account.TRANSACTION_FEE;

public class BankAccount implements Saveable {
    private String name; // Name of bank account owner
    public ArrayList<Account> accounts; // Types of accounts the owner has opened


    public BankAccount(String owner, List<Account> accounts) {
        this.name = owner;
        this.accounts = (ArrayList<Account>) accounts;
    }


    // MODIFIES: this
    // EFFECTS: transfers requested amount from acc1 to acc2
    public static boolean transferFunds(Account acc1, Account acc2, double amount)
            throws NegativeAmount, NegativeBalance, ExceedMaxAmount {
        if (amount < 0) {
            throw new NegativeAmount();
        } else if (amount > acc1.getBalance()) {
            throw new NegativeBalance();
        } else {
            if ((acc2.balance + amount) > MAXIMUM_BALANCE) {
                throw new ExceedMaxAmount();
            } else {
                acc1.balance = acc1.balance - amount;
                acc2.balance = acc2.balance + amount - TRANSACTION_FEE;
                return true;
            }
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.println(name);
        for (Account acc : accounts) {
            if (acc instanceof RegisteredEducationSavingsPlan) {
                printWriter.print(acc.getType());
                printWriter.print(Reader.DELIMITER);
                printWriter.print(acc.pin);
                printWriter.print(Reader.DELIMITER);
                printWriter.print(acc.balance);
                printWriter.print(Reader.DELIMITER);
                printWriter.println(((RegisteredEducationSavingsPlan) acc).getChildName());
            } else {
                printWriter.print(acc.getType());
                printWriter.print(Reader.DELIMITER);
                printWriter.print(acc.pin);
                printWriter.print(Reader.DELIMITER);
                printWriter.println(acc.balance);
            }
        }
    }
}
