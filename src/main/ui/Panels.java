package ui;

import javax.swing.*;
import java.awt.*;

public class Panels {

    static JTextField pin;
    static JTextField name;
    static JTextField confirmPin;
    static JTextField child;
    static JTextField initialDeposit;
    static JTextField amountDeposit;
    static JTextField amountWithdraw;
    static JTextField amountTransfer;
    static String ownerName;

    public Panels() {
        pin = new JTextField(15);
        name = new JTextField(15);
        child = new JTextField(15);
        initialDeposit = new JTextField(15);
        confirmPin = new JTextField(15);
        amountDeposit = new JTextField(15);
        amountWithdraw = new JTextField(15);
        amountTransfer = new JTextField(15);
    }

    public JPanel createStartingPanel() {
        JPanel startingPanel = new JPanel();
        JLabel greeting = new JLabel("Hello!", JLabel.CENTER);
        greeting.setPreferredSize(new Dimension(125, 50));
        startingPanel.add(greeting);
        return startingPanel;
    }

    public JPanel createNewAccountPanel() {
        JPanel accountCreation = new JPanel();
        JLabel askName = new JLabel("Please type your name:");
        accountCreation.add(askName);
        accountCreation.add(name);
        return accountCreation;
    }

    public JPanel createCompletePanel() {
        JPanel processComplete = new JPanel();
        JLabel complete = new JLabel("Complete!");
        processComplete.add(complete);
        return processComplete;
    }

    public JPanel createBankingOperationsPanel() {
        JPanel bankOperations = new JPanel();
        JLabel operations = new JLabel("Banking", JLabel.CENTER);
        bankOperations.add(operations);
        return bankOperations;
    }

    public JPanel createInfoPanel() {
        JPanel info = new JPanel();
        JLabel owner = new JLabel("                  " + ownerName + "                  ", JLabel.CENTER);
        info.add(owner);
        return info;
    }

    public JPanel createAccountTypePanel() {
        JPanel createAccountType = new JPanel();
        JLabel choices = new JLabel("Please choose an account type to open:", JLabel.CENTER);
        createAccountType.add(choices);
        return createAccountType;
    }

    public JPanel createCreationErrorPanel() {
        JPanel creationError = new JPanel();
        JLabel alreadyCreated = new JLabel("This account type has already been created.");
        creationError.add(alreadyCreated);
        return creationError;
    }

    public JPanel createInvalidInputPanel() {
        JPanel invalidMessage = new JPanel();
        JLabel invalid = new JLabel("Error: invalid input.", JLabel.CENTER);
        invalidMessage.add(invalid);
        return invalidMessage;
    }

    public JPanel createChooseAccountPanel() {
        JPanel chooseAccount = new JPanel();
        JLabel choices = new JLabel("Choose account to manage:", JLabel.CENTER);
        chooseAccount.add(choices);
        return chooseAccount;
    }

    public JPanel createInvalidAccountPanel() {
        JPanel invalidAccount = new JPanel();
        JLabel errorAccount = new JLabel("Error: account type has not been created yet.");
        invalidAccount.add(errorAccount);
        return invalidAccount;
    }

    public JPanel createNewPinPanel() {
        JPanel enterNewPin = new JPanel();
        JLabel enterPin = new JLabel("PIN:", JLabel.LEFT);
        enterNewPin.add(enterPin);
        enterNewPin.add(pin);
        return enterNewPin;
    }

    public JPanel createNewRespPanel() {
        JPanel enterNewPin = new JPanel();
        JLabel enterPin = new JLabel("PIN:", JLabel.LEFT);
        JLabel enterChild = new JLabel("Child name:", JLabel.LEFT);
        JLabel enterInitialDeposit = new JLabel("Initial Deposit:", JLabel.LEFT);
        enterNewPin.add(enterPin);
        enterNewPin.add(pin);
        enterNewPin.add(enterChild);
        enterNewPin.add(child);
        enterNewPin.add(enterInitialDeposit);
        enterNewPin.add(initialDeposit);
        return enterNewPin;
    }

    public JPanel createCheckPinPanel() {
        JPanel matchPin = new JPanel();
        JLabel askPin = new JLabel("Please enter your PIN:", JLabel.CENTER);
        matchPin.add(askPin);
        return matchPin;
    }

    public JPanel createInvalidPinPanel() {
        JPanel errorPin = new JPanel();
        JLabel wrongPin = new JLabel("Invalid PIN.", JLabel.CENTER);
        errorPin.add(wrongPin);
        return errorPin;
    }

    public JPanel createDepositPanel() {
        JPanel displayDeposit = new JPanel();
        JLabel askDeposit = new JLabel("Enter amount you want to deposit:");
        displayDeposit.add(askDeposit);
        displayDeposit.add(amountDeposit);
        return displayDeposit;
    }

    public JPanel createWithdrawPanel() {
        JPanel displayWithdraw = new JPanel();
        JLabel askWithdraw = new JLabel("Enter amount you want to withdraw:");
        displayWithdraw.add(askWithdraw);
        displayWithdraw.add(amountWithdraw);
        return displayWithdraw;
    }

    public JPanel createIncorrectNamePanel() {
        JPanel wrongName = new JPanel();
        JLabel name = new JLabel("Incorrect Name.");
        wrongName.add(name);
        return wrongName;
    }

    public JPanel createDisplayChequePanel() {
        JPanel displayCheque = new JPanel();
        JLabel askCheque = new JLabel("Enter the following information:");
        JLabel askValue = new JLabel("Cheque Value:");
        displayCheque.add(askCheque);
        displayCheque.add(askValue);
        return displayCheque;
    }

    public JPanel addNameToCheque(JPanel d) {
        JLabel askName = new JLabel("Recipient:");
        JTextField name = new JTextField(15);
        d.add(askName);
        d.add(name);
        return d;
    }

    public JPanel createTransferFromPanel() {
        JPanel from = new JPanel();
        JLabel select1 = new JLabel("Select account to transfer from:");
        from.add(select1);
        return from;
    }

    public JPanel createTransferToPanel() {
        JPanel to = new JPanel();
        JLabel select2 = new JLabel("Select account to transfer to:");
        to.add(select2);
        return to;
    }

    public JPanel createTransferAmountPanel() {
        JPanel transfer = new JPanel();
        JLabel askAmount = new JLabel("Enter amount to be transferred");
        transfer.add(askAmount);
        transfer.add(amountTransfer);
        return transfer;
    }

    public JPanel createNoAccountPanel() {
        JPanel noAccount = new JPanel();
        JLabel noAcc = new JLabel("Account not found: Please create a new account.");
        noAccount.add(noAcc);
        return noAccount;
    }

    public JPanel createAccountFoundPanel() {
        JPanel loadAccount = new JPanel();
        JLabel loadAcc = new JLabel("Your account data has been loaded.");
        loadAccount.add(loadAcc);
        return loadAccount;
    }

}