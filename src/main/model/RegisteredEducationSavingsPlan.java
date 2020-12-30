package model;

import exceptions.NegativeAmount;
import exceptions.NegativeBalance;

public class RegisteredEducationSavingsPlan extends Account {
    private String type; // type of account
    private String child; // child the RESP is for

    public RegisteredEducationSavingsPlan(String accountPin, String child, double deposit) {
        super(accountPin, STARTING_BALANCE);
        this.type = "RESP";
        this.child = child;
        this.balance = deposit;
    }

    @Override
    public String getType() {
        return type;
    }

    // MODIFIES: this
    // EFFECTS: takes amount from balance if there is enough balance, but only if
    //          the child is the one that is withdrawing it
    public boolean withdraw(double amount, String name) throws NegativeBalance, NegativeAmount {
        if (child.equals(name)) {
            super.withdraw(amount);
            return true;
        }
        return false;
    }

    // EFFECTS: returns child's name
    public String getChildName() {
        return child;
    }
}
