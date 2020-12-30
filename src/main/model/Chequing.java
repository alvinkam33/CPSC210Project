package model;

import exceptions.NegativeAmount;
import exceptions.NegativeBalance;

public class Chequing extends Account {
    private String type; // type of account

    public Chequing(String accountPin, double initialBalance) {
        super(accountPin, initialBalance);
        this.type = "Chequing";
    }

    @Override
    // EFFECTS: returns type of account
    public String getType() {
        return type;
    }

    // MODIFIES: this
    // EFFECTS: creates a cheque with given amount paid to the order of requested recipient
    public boolean writeCheque(double amount) throws NegativeBalance, NegativeAmount {
        if (amount < 0) {
            throw new NegativeAmount();
        } else if (amount > balance) {
            throw new NegativeBalance();
        } else {
            balance = balance - amount - TRANSACTION_FEE;
            return true;
        }
    }
}
