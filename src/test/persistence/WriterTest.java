package persistence;

import exceptions.ExceedMaxAmount;
import exceptions.NegativeAmount;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static model.Account.STARTING_BALANCE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WriterTest {
    private static final String TEST_FILE = "./data/testAccount.txt";
    private Writer testWriter;
    BankAccount b1;
    BankAccount b2;
    Chequing a1;
    Savings a2;
    RegisteredEducationSavingsPlan a3;


    @BeforeEach
    void runBefore() {
        try {
            testWriter = new Writer(new File(TEST_FILE));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        b1 = new BankAccount("Johnny Kam", new ArrayList<>());
        b2 = new BankAccount("Anthony", new ArrayList<>());
        a1 = new Chequing("123456", STARTING_BALANCE);
        a2 = new Savings("987654", STARTING_BALANCE);
        a3 = new RegisteredEducationSavingsPlan("192837", "Alvin Kam", 50000);
        b1.accounts.add(a1);
        b1.accounts.add(a2);
        b1.accounts.add(a3);
        try {
            a1.deposit(100000);
        } catch (NegativeAmount | ExceedMaxAmount e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteAccounts() {
        // save BankAccount to file
        testWriter.write(b1);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            BankAccount ba = Reader.readAccount(new File(TEST_FILE));
            assertEquals("Johnny Kam", ba.getName());
            Account chequing = ba.accounts.get(0);
            assertEquals(99999.75, chequing.getBalance());
            assertEquals("123456", chequing.getPin());

            Account savings = ba.accounts.get(1);
            assertEquals(0, savings.getBalance());
            assertEquals("987654", savings.getPin());

            RegisteredEducationSavingsPlan resp = (RegisteredEducationSavingsPlan) ba.accounts.get(2);
            assertEquals(50000, resp.getBalance());
            assertEquals("192837", resp.getPin());
            assertEquals("Alvin Kam", resp.getChildName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}