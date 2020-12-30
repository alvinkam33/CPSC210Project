package ui;

import exceptions.ExceedMaxAmount;
import exceptions.NegativeAmount;
import exceptions.NegativeBalance;
import model.*;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class BankAccountInterface extends JFrame implements ActionListener {
    private BankAccount bank;
    private JLabel accNoInfo;
    private JButton ok;
    private JButton enter;
    private JButton back;
    private JButton chequing;
    private JButton savings;
    private JButton resp;
    private Account acc1;
    private Account acc2;
    private static final String ACCOUNTS_FILE = "./data/account.txt";
    private Panels panels;
    private Sounds sounds;

    public BankAccountInterface() {
        super("Bank Account App");
        accNoInfo = new JLabel("You have not opened any accounts.");
        panels = new Panels();
        sounds = new Sounds();
        setButtons();

    }

    private void setButtons() {
        ok = new JButton("Ok");
        ok.setActionCommand("ok");
        ok.addActionListener(this);
        enter = new JButton("Enter");
        enter.addActionListener(this);
        back = new JButton("Return");
        back.setActionCommand("return");
        back.addActionListener(this);
        chequing = new JButton("Chequing");
        chequing.addActionListener(this);
        savings = new JButton("Savings");
        savings.addActionListener(this);
        resp = new JButton("RESP");
        resp.addActionListener(this);
        runBankAccountGUI();
    }

    private void runBankAccountGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel startingPanel = panels.createStartingPanel();
        JButton newAccount = new JButton("New Account");
        newAccount.setActionCommand("create");
        newAccount.addActionListener(this);
        JButton loadAccount = new JButton("Load Account");
        loadAccount.setActionCommand("load");
        loadAccount.addActionListener(this);
        startingPanel.add(newAccount);
        startingPanel.add(loadAccount);
        setContentPane(startingPanel);
        setSize(250, 175);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "create":
                createNewAccount();
                break;
            case "load":
                loadAccount();
                break;
            case "name":
                newBankAccount();
                break;
            case "ok":
                displayBankOperations();
                break;
            case "open":
                openAccount();
                break;
            case "c":
                openChequing();
                break;
            case "s":
                openSavings();
                break;
            case "r":
                openResp();
                break;
            case "set chequing":
                completeChequing();
                break;
            case "set savings":
                completeSavings();
                break;
            default:
                actionPerformedTwo(e);
                break;
        }
    }

    public void actionPerformedTwo(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "manage":
                chooseAccount();
                break;
            case "mc":
                askChequingPin();
                break;
            case "ms":
                askSavingsPin();
                break;
            case "mr":
                askRespPin();
                break;
            case "chequingpin":
                matchChequingPin();
                break;
            case "savingspin":
                matchSavingsPin();
                break;
            case "resppin":
                matchRespPin();
                break;
            case "cd":
                depositChequing();
                break;
            case "cw":
                withdrawChequing();
                break;
            case "writecheque":
                writeCheque();
                break;
            default:
                actionPerformedThree(e);
                break;
        }
    }

    public void actionPerformedThree(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "sd":
                depositSavings();
                break;
            case "sw":
                withdrawSavings();
                break;
            case "rw":
                checkChild();
                break;
            case "confirmchild":
                correctChild();
                break;
            case "r.withdraw":
                doRespWithdraw();
                break;
            case "c.amountdeposit":
                doChequingDeposit();
                break;
            case "s.amountdeposit":
                doSavingsDeposit();
                break;
            case "c.withdraw":
                doChequingWithdraw();
                break;
            case "s.withdraw":
                doSavingsWithdraw();
                break;
            case "c.cheque":
                completeCheque();
                break;
            default:
                actionPerformedFour(e);
                break;
        }
    }

    public void actionPerformedFour(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "c.ok":
                displayChequingOperations();
                break;
            case "s.ok":
                displaySavingsOperations();
                break;
            case "r.ok":
                displayRespOperations();
                break;
            case "transfer":
                transferFrom();
                break;
            case "t.from.c":
                acc1 = checkChequing();
                transferTo();
                break;
            case "t.from.s":
                acc1 = checkSavings();
                transferTo();
                break;
            case "t.to.c":
                acc2 = checkChequing();
                transferAmount();
                break;
            case "t.to.s":
                acc2 = checkSavings();
                transferAmount();
                break;
            default:
                actionPerformedFive(e);
                break;
        }
    }

    public void actionPerformedFive(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "t.to.r":
                acc2 = checkResp();
                transferAmount();
                break;
            case "t.amount":
                doTransfer();
                break;
            case "return":
                displayBankOperations();
                break;
            case "info":
                displayInfo();
                break;
            case "save":
                saveAccountData();
                break;
            case "set resp":
                completeResp();
                break;
        }
    }

    private void createNewAccount() {
        JPanel accountCreation = panels.createNewAccountPanel();
        JButton setName = new JButton("Set Name");
        setName.setActionCommand("name");
        setName.addActionListener(this);
        accountCreation.add(setName);
        setContentPane(accountCreation);
        setSize(250, 175);
        setVisible(true);
    }

    private JPanel displayComplete() {
        sounds.playCompleteSound();
        JPanel processComplete = panels.createCompletePanel();
        processComplete.add(ok);
        setSize(100, 100);
        return processComplete;
    }

    private void newBankAccount() {
        bank = new BankAccount(Panels.name.getText(), new ArrayList<>());
        Panels.ownerName = bank.getName();
        setContentPane(displayComplete());
    }

    private void displayBankOperations() {
        JPanel bankOperations = panels.createBankingOperationsPanel();
        addBankOperationsButtons(bankOperations);
        setContentPane(bankOperations);
        setSize(200, 250);
    }

    private void addBankOperationsButtons(JPanel p) {
        JButton open = new JButton("Open an Account");
        open.setActionCommand("open");
        open.addActionListener(this);
        JButton manage = new JButton("Manage an Account");
        manage.setActionCommand("manage");
        manage.addActionListener(this);
        JButton info = new JButton("Get Info");
        info.setActionCommand("info");
        info.addActionListener(this);
        JButton transfer = new JButton("Transfer Funds");
        transfer.setActionCommand("transfer");
        transfer.addActionListener(this);
        JButton save = new JButton("Save Account Data");
        save.setActionCommand("save");
        save.addActionListener(this);
        p.add(open);
        p.add(manage);
        p.add(info);
        p.add(transfer);
        p.add(save);
    }

    private void openAccount() {
        JPanel createAccountType = panels.createAccountTypePanel();
        chequing.setActionCommand("c");
        savings.setActionCommand("s");
        resp.setActionCommand("r");
        createAccountType.add(chequing);
        createAccountType.add(savings);
        createAccountType.add(resp);
        setContentPane(createAccountType);
        setSize(300, 150);
    }

    private Chequing checkChequing() {
        Chequing alreadyMade = null;
        for (Account a : bank.accounts) {
            if (a instanceof Chequing) {
                alreadyMade = (Chequing) a;
            }
        }
        return alreadyMade;
    }

    private Savings checkSavings() {
        Savings alreadyMade = null;
        for (Account a : bank.accounts) {
            if (a instanceof Savings) {
                alreadyMade = (Savings) a;
            }
        }
        return alreadyMade;
    }

    private RegisteredEducationSavingsPlan checkResp() {
        RegisteredEducationSavingsPlan alreadyMade = null;
        for (Account a : bank.accounts) {
            if (a instanceof RegisteredEducationSavingsPlan) {
                alreadyMade = (RegisteredEducationSavingsPlan) a;
            }
        }
        return alreadyMade;
    }

    private void openChequing() {
        if (!(checkChequing() == null)) {
            accountAlreadyMade();
        } else {
            newChequing();
        }
    }

    private void openSavings() {
        if (!(checkSavings() == null)) {
            accountAlreadyMade();
        } else {
            newSavings();
        }
    }

    private void openResp() {
        if (!(checkResp() == null)) {
            accountAlreadyMade();
        } else {
            newResp();
        }
    }

    private void newChequing() {
        JPanel enterNewPin = panels.createNewPinPanel();
        JButton setPin = new JButton("Set Pin");
        setPin.setActionCommand("set chequing");
        setPin.addActionListener(this);
        enterNewPin.add(setPin);
        setContentPane(enterNewPin);
        setVisible(true);
    }

    private void newSavings() {
        JPanel enterNewPin = panels.createNewPinPanel();
        JButton setPin = new JButton("Set Pin");
        setPin.setActionCommand("set savings");
        setPin.addActionListener(this);
        enterNewPin.add(setPin);
        setContentPane(enterNewPin);
        setVisible(true);
    }

    private void newResp() {
        JPanel enterNewPin = panels.createNewRespPanel();
        JButton setInfo = new JButton("Set Info");
        setInfo.setActionCommand("set resp");
        setInfo.addActionListener(this);
        enterNewPin.add(setInfo);
        setContentPane(enterNewPin);
        setVisible(true);
    }

    private void completeChequing() {
        Chequing c = new Chequing(Panels.pin.getText(), 0);
        bank.accounts.add(c);
        setContentPane(displayComplete());
    }

    private void completeSavings() {
        Savings s = new Savings(Panels.pin.getText(), 0);
        bank.accounts.add(s);
        setContentPane(displayComplete());
    }

    private void completeResp() {
        try {
            Double.parseDouble(Panels.initialDeposit.getText());
        } catch (NumberFormatException e) {
            invalidInput();
        }
        if ((Double.parseDouble(Panels.initialDeposit.getText())) > 100000.0
                | (Double.parseDouble(Panels.initialDeposit.getText())) < 0) {
            invalidInput();
        } else {
            RegisteredEducationSavingsPlan r = new RegisteredEducationSavingsPlan(Panels.pin.getText(),
                    Panels.child.getText(), Double.parseDouble(Panels.initialDeposit.getText()));
            bank.accounts.add(r);
            setContentPane(displayComplete());
        }
    }

    private void accountAlreadyMade() {
        sounds.playErrorSound();
        JPanel creationError = panels.createCreationErrorPanel();
        ok.setActionCommand("ok");
        creationError.add(ok);
        setContentPane(creationError);
        setSize(300, 150);
        setVisible(true);
    }

    private void invalidInput() {
        sounds.playErrorSound();
        JPanel invalidMessage = panels.createInvalidInputPanel();
        invalidMessage.add(ok);
        setContentPane(invalidMessage);
        setSize(150, 100);
        setVisible(true);
    }

    private void chooseAccount() {
        JPanel chooseAccount = panels.createChooseAccountPanel();
        chequing.setActionCommand("mc");
        savings.setActionCommand("ms");
        resp.setActionCommand("mr");
        chooseAccount.add(chequing);
        chooseAccount.add(savings);
        chooseAccount.add(resp);
        setContentPane(chooseAccount);
        setSize(175, 150);
        setVisible(true);
    }

    private void accountNotCreated() {
        sounds.playErrorSound();
        JPanel invalidAccount = panels.createInvalidAccountPanel();
        ok.setActionCommand("ok");
        invalidAccount.add(ok);
        setContentPane(invalidAccount);
        setSize(300, 150);
        setVisible(true);
    }

    private void askChequingPin() {
        if (checkChequing() == null) {
            accountNotCreated();
        } else {
            JPanel matchPin = panels.createCheckPinPanel();
            enter.setActionCommand("chequingpin");
            matchPin.add(Panels.confirmPin);
            matchPin.add(enter);
            setContentPane(matchPin);
            setSize(200, 150);
            setVisible(true);
        }
    }

    private void askSavingsPin() {
        if (checkSavings() == null) {
            accountNotCreated();
        } else {
            JPanel matchPin = panels.createCheckPinPanel();
            enter.setActionCommand("savingspin");
            matchPin.add(Panels.confirmPin);
            matchPin.add(enter);
            setContentPane(matchPin);
            setSize(200, 150);
            setVisible(true);
        }
    }

    private void askRespPin() {
        if (checkResp() == null) {
            accountNotCreated();
        } else {
            JPanel matchPin = panels.createCheckPinPanel();
            enter.setActionCommand("resppin");
            matchPin.add(Panels.confirmPin);
            matchPin.add(enter);
            setContentPane(matchPin);
            setSize(200, 150);
            setVisible(true);
        }
    }

    private void matchChequingPin() {
        if (!Panels.confirmPin.getText().equals(checkChequing().getPin())) {
            invalidPin();
        } else {
            displayChequingOperations();
        }
    }

    private void matchSavingsPin() {
        if (!Panels.confirmPin.getText().equals(checkSavings().getPin())) {
            invalidPin();
        } else {
            displaySavingsOperations();
        }
    }

    private void matchRespPin() {
        if (!Panels.confirmPin.getText().equals(checkResp().getPin())) {
            invalidPin();
        } else {
            displayRespOperations();
        }
    }

    private void invalidPin() {
        sounds.playErrorSound();
        JPanel errorPin = panels.createInvalidPinPanel();
        ok.setActionCommand("ok");
        errorPin.add(ok);
        setContentPane(errorPin);
        setVisible(true);
    }

    private void displayChequingOperations() {
        JPanel chequingOperations = new JPanel();
        JLabel chequingInfo = new JLabel("Chequing account balance: $" + checkChequing().getBalance(), JLabel.CENTER);
        JButton chequingDeposit = new JButton("Deposit");
        chequingDeposit.setActionCommand("cd");
        chequingDeposit.addActionListener(this);
        JButton chequingWithdraw = new JButton("Withdraw");
        chequingWithdraw.setActionCommand("cw");
        chequingWithdraw.addActionListener(this);
        JButton cheque = new JButton("Write a Cheque");
        cheque.setActionCommand("writecheque");
        cheque.addActionListener(this);
        chequingOperations.add(chequingInfo);
        chequingOperations.add(chequingDeposit);
        chequingOperations.add(chequingWithdraw);
        chequingOperations.add(cheque);
        chequingOperations.add(back);
        setContentPane(chequingOperations);
        setSize(225, 200);
        setVisible(true);
    }

    private void displaySavingsOperations() {
        JPanel savingsOperations = new JPanel();
        JLabel savingsInfo = new JLabel("Savings account balance: $" + checkSavings().getBalance(), JLabel.CENTER);
        JLabel interestRate = new JLabel("Interest Rate: " + checkSavings().getInterestRate(), JLabel.CENTER);
        JButton savingsDeposit = new JButton("Deposit");
        savingsDeposit.setActionCommand("sd");
        savingsDeposit.addActionListener(this);
        JButton savingsWithdraw = new JButton("Withdraw");
        savingsWithdraw.setActionCommand("sw");
        savingsWithdraw.addActionListener(this);
        savingsOperations.add(savingsInfo);
        savingsOperations.add(interestRate);
        savingsOperations.add(savingsDeposit);
        savingsOperations.add(savingsWithdraw);
        savingsOperations.add(back);
        setContentPane(savingsOperations);
        setSize(215, 200);
        setVisible(true);
    }

    private void displayRespOperations() {
        JPanel respOperations = new JPanel();
        JLabel respInfo = new JLabel("RESP account balance: $" + checkResp().getBalance(), JLabel.CENTER);
        JButton respWithdraw = new JButton("Withdraw");
        respWithdraw.setActionCommand("rw");
        respWithdraw.addActionListener(this);
        respOperations.add(respInfo);
        respOperations.add(respWithdraw);
        respOperations.add(back);
        setContentPane(respOperations);
        setSize(225, 150);
        setVisible(true);
    }

    private void displayInfo() {
        JPanel info = panels.createInfoPanel();
        Chequing chequing = checkChequing();
        Savings savings = checkSavings();
        RegisteredEducationSavingsPlan resp = checkResp();
        if (chequing == null && savings == null && resp == null) {
            info.add(accNoInfo);
        } else {
            if (!(chequing == null)) {
                JLabel cheqInfo = new JLabel("         " + InfoDisplayer.chequingInfo(chequing) + "         ");
                info.add(cheqInfo);
            }
            if (!(savings == null)) {
                JLabel savingsInfo = new JLabel("         " + InfoDisplayer.savingsInfo(savings) + "         ");
                info.add(savingsInfo);
            }
            if (!(resp == null)) {
                JLabel respInfo = new JLabel(InfoDisplayer.respInfo(resp));
                info.add(respInfo);
            }
        }
        displayInfoMaximum(info);
    }

    private void displayInfoMaximum(JPanel i) {
        JLabel maximum = new JLabel("Maximum allowable balance on each account is $100,000.");
        i.add(maximum);
        ok.setActionCommand("ok");
        i.add(ok);
        setContentPane(i);
        setSize(375, 200);
        setVisible(true);
    }


    private void depositChequing() {
        JPanel deposit = panels.createDepositPanel();
        enter.setActionCommand("c.amountdeposit");
        deposit.add(enter);
        setContentPane(deposit);
        setSize(250, 150);
        setVisible(true);
    }

    private void depositSavings() {
        JPanel deposit = panels.createDepositPanel();
        enter.setActionCommand("s.amountdeposit");
        deposit.add(enter);
        setContentPane(deposit);
        setSize(250, 150);
        setVisible(true);
    }

    private void doChequingDeposit() {
        Exception exception = null;
        try {
            checkChequing().deposit(Double.parseDouble(Panels.amountDeposit.getText()));
        } catch (ExceedMaxAmount | NumberFormatException | NegativeAmount e) {
            exception = e;
        }
        if (exception == null) {
            chequingComplete();
        } else {
            invalidChequingInput();
        }
    }

    private void doSavingsDeposit() {
        Exception exception = null;
        try {
            checkSavings().deposit(Double.parseDouble(Panels.amountDeposit.getText()));
        } catch (ExceedMaxAmount | NumberFormatException | NegativeAmount e) {
            exception = e;
        }
        if (exception == null) {
            savingsComplete();
        } else {
            invalidSavingsInput();
        }
    }

    private void withdrawChequing() {
        JPanel withdraw = panels.createWithdrawPanel();
        enter.setActionCommand("c.withdraw");
        withdraw.add(enter);
        setContentPane(withdraw);
        setSize(250, 150);
        setVisible(true);
    }

    private void withdrawSavings() {
        JPanel withdraw = panels.createWithdrawPanel();
        enter.setActionCommand("s.withdraw");
        withdraw.add(enter);
        setContentPane(withdraw);
        setSize(250, 150);
        setVisible(true);
    }

    private void checkChild() {
        JPanel child = new JPanel();
        JLabel amChild = new JLabel("You must be the child (the beneficiary) to conduct a withdrawl.");
        JLabel askName = new JLabel("Name:");
        enter.setActionCommand("confirmchild");
        child.add(amChild);
        child.add(askName);
        child.add(Panels.name);
        child.add(enter);
        setContentPane(child);
        setSize(400, 150);
        setVisible(true);
    }

    private void correctChild() {
        if (!(Panels.name.getText().equals(checkResp().getChildName()))) {
            incorrectName();
        } else {
            withdrawResp();
        }
    }

    private void incorrectName() {
        sounds.playErrorSound();
        JPanel wrongName = panels.createIncorrectNamePanel();
        ok.setActionCommand("r.ok");
        wrongName.add(ok);
        setContentPane(wrongName);
        setSize(150, 100);
        setVisible(true);
    }

    private void withdrawResp() {
        JPanel withdraw = panels.createWithdrawPanel();
        enter.setActionCommand("r.withdraw");
        withdraw.add(Panels.amountWithdraw);
        withdraw.add(enter);
        setContentPane(withdraw);
        setSize(300, 100);
        setVisible(true);
    }

    private void doChequingWithdraw() {
        Exception exception = null;
        try {
            checkChequing().withdraw(Double.parseDouble(Panels.amountWithdraw.getText()));
        } catch (NumberFormatException | NegativeAmount | NegativeBalance e) {
            exception = e;
        }
        if (exception == null) {
            chequingComplete();
        } else {
            invalidChequingInput();
        }
    }

    private void doSavingsWithdraw() {
        Exception exception = null;
        try {
            checkSavings().withdraw(Double.parseDouble(Panels.amountWithdraw.getText()));
        } catch (NumberFormatException | NegativeAmount | NegativeBalance e) {
            exception = e;
        }
        if (exception == null) {
            savingsComplete();
        } else {
            invalidSavingsInput();
        }
    }

    private void doRespWithdraw() {
        Exception exception = null;
        try {
            checkResp().withdraw(Double.parseDouble(Panels.amountWithdraw.getText()));
        } catch (NegativeBalance | NegativeAmount | NumberFormatException e) {
            exception = e;
        }
        if (exception == null) {
            respComplete();
        } else {
            invalidRespInput();
        }
    }

    private void writeCheque() {
        JPanel displayCheque = panels.createDisplayChequePanel();
        enter.setActionCommand("c.cheque");
        displayCheque.add(Panels.amountWithdraw);
        JPanel cheque = panels.addNameToCheque(displayCheque);
        cheque.add(enter);
        setContentPane(cheque);
        setVisible(true);
    }

    private void completeCheque() {
        Exception exception = null;
        try {
            checkChequing().writeCheque(Double.parseDouble(Panels.amountWithdraw.getText()));
        } catch (NumberFormatException | NegativeAmount | NegativeBalance e) {
            exception = e;
        }
        if (exception == null) {
            chequingComplete();
        } else {
            invalidChequingInput();
        }
    }

    private void transferFrom() {
        JPanel from = panels.createTransferFromPanel();
        chequing.setActionCommand("t.from.c");
        savings.setActionCommand("t.from.s");
        from.add(chequing);
        from.add(savings);
        setContentPane(from);
        setSize(200, 150);
        setVisible(true);
    }

    private void transferTo() {
        JPanel to = panels.createTransferToPanel();
        chequing.setActionCommand("t.to.c");
        savings.setActionCommand("t.to.s");
        resp.setActionCommand("t.to.r");
        to.add(chequing);
        to.add(savings);
        to.add(resp);
        setContentPane(to);
        setVisible(true);
    }

    private void transferAmount() {
        JPanel transfer = panels.createTransferAmountPanel();
        enter.setActionCommand("t.amount");
        transfer.add(enter);
        setContentPane(transfer);
        setVisible(true);
    }

    private void doTransfer() {
        Exception exception = null;
        if (acc1 == acc2 | acc1 == null | acc2 == null) {
            invalidInput();
        } else {
            try {
                BankAccount.transferFunds(acc1, acc2, Double.parseDouble(Panels.amountTransfer.getText()));
            } catch (NegativeBalance | ExceedMaxAmount | NegativeAmount | NumberFormatException e) {
                exception = e;
            }
            if (!(exception == null)) {
                invalidInput();
            } else {
                ok.setActionCommand("ok");
                setContentPane(displayComplete());
            }
        }
    }

    private void chequingComplete() {
        JPanel chequingTransaction = displayComplete();
        ok.setActionCommand("c.ok");
        setContentPane(chequingTransaction);
        setVisible(true);
    }

    private void savingsComplete() {
        JPanel savingsTransaction = displayComplete();
        ok.setActionCommand("s.ok");
        setContentPane(savingsTransaction);
        setVisible(true);
    }

    private void respComplete() {
        JPanel respTransaction = displayComplete();
        ok.setActionCommand("r.ok");
        setContentPane(respTransaction);
        setVisible(true);
    }

    private void invalidChequingInput() {
        ok.setActionCommand("c.ok");
        invalidInput();
    }

    private void invalidSavingsInput() {
        ok.setActionCommand("s.ok");
        invalidInput();
    }

    private void invalidRespInput() {
        ok.setActionCommand("r.ok");
        invalidInput();
    }

    private void saveAccountData() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            writer.write(bank);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        setContentPane(displayComplete());
    }

    private void loadAccount() {
        try {
            bank = Reader.readAccount(new File(ACCOUNTS_FILE));
            Panels.ownerName = bank.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bank.getName().equals("")) {
            noAccountFound();
        } else {
            accountFound();
        }
    }

    private void noAccountFound() {
        sounds.playErrorSound();
        JPanel noAccount = panels.createNoAccountPanel();
        ok.setActionCommand("create");
        noAccount.add(ok);
        setContentPane(noAccount);
        setSize(350, 100);
        setVisible(true);
    }

    private void accountFound() {
        sounds.playCompleteSound();
        JPanel loadAccount = panels.createAccountFoundPanel();
        ok.setActionCommand("ok");
        loadAccount.add(ok);
        setContentPane(loadAccount);
        setSize(250, 100);
        setVisible(true);
    }
}
