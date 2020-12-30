package model;

public class Savings extends Account {
    private String type; //type of account
    private String interest; //interest rate
    public static String INTEREST_RATE = "3.0%";

    public Savings(String accountPin, double initialBalance) {
        super(accountPin, initialBalance);
        this.type = "Savings";
        this.interest = INTEREST_RATE;
    }

    @Override
    // EFFECTS: returns type of account
    public String getType() {
        return type;
    }

    // EFFECTS: returns interest rate
    public String getInterestRate() {
        return interest;
    }


}
