package persistence;

import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read account data from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns bank account parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static BankAccount readAccount(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    // where first row is account name and
    // subsequent rows contain information of account types
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a bank account parsed from list of string
    private static BankAccount parseContent(List<String> fileContent) {
        String name = "";
        List<Account> accounts = new ArrayList<>();
        int counter = 0;

        for (String line : fileContent) {
            if (counter == 0) {
                name = parseName(line);
                counter++;
            } else {
                ArrayList<String> lineComponents = splitString(line);
                accounts.add(parseAccount(lineComponents));
            }
        }

        return new BankAccount(name, accounts);
    }

    private static String parseName(String line) {
        return line;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components past the first row have either size 3 or 4
    // size 3 indicates a chequing/savings account (type, pin, balance)
    // size 4 indicates an RESP (type, pin, balance, child name)
    // EFFECTS: takes information from each component and creates corresponding account type with given information
    private static Account parseAccount(List<String> components) {
        String pin;
        double balance;
        String childName;
        if (components.get(0).equals("Chequing")) {
            pin = components.get(1);
            balance = Double.parseDouble(components.get(2));
            return new Chequing(pin, balance);
        } else if (components.get(0).equals("Savings")) {
            pin = components.get(1);
            balance = Double.parseDouble(components.get(2));
            return new Savings(pin, balance);
        } else {
            pin = components.get(1);
            balance = Double.parseDouble(components.get(2));
            childName = components.get(3);
            return new RegisteredEducationSavingsPlan(pin, childName, balance);
        }
    }
}
