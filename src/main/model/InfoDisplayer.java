package model;

import java.util.ArrayList;

public class InfoDisplayer {

    public static String getInfo(ArrayList<Account> accounts) {
        StringBuilder info = new StringBuilder();
        String type;
        String balance;
        if (accounts.equals(new ArrayList<>())) {
            info = new StringBuilder("You have not opened any accounts.");
        } else {
            for (Account acc : accounts) {
                type = acc.getType();
                balance = Double.toString(acc.getBalance());
                info.append(type).append(" account has $").append(balance).append(" \n");
            }
        }
        return info.toString();
    }

    public static String chequingInfo(Chequing chequing) {
        return "Chequing account has $" + chequing.getBalance();
    }

    public static String savingsInfo(Savings savings) {
        return "Savings account has $" + savings.getBalance();
    }

    public static String respInfo(RegisteredEducationSavingsPlan registeredEducationSavingsPlan) {
        return "RESP account has $" + registeredEducationSavingsPlan.getBalance();
    }
}
