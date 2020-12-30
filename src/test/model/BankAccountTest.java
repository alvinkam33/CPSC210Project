package model;

import exceptions.ExceedMaxAmount;
import exceptions.NegativeAmount;
import exceptions.NegativeBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Account.STARTING_BALANCE;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    BankAccount b1;
    BankAccount b2;
    Chequing a1;
    Savings a2;
    RegisteredEducationSavingsPlan a3;
    InfoDisplayer info;


    @BeforeEach
    void runBefore() {
        b1 = new BankAccount("Johnny Kam", new ArrayList<>());
        b2 = new BankAccount("Anthony", new ArrayList<>());
        a1 = new Chequing("123456", STARTING_BALANCE);
        a2 = new Savings("987654", STARTING_BALANCE);
        a3 = new RegisteredEducationSavingsPlan("192837", "Alvin Kam", 50000);
        info = new InfoDisplayer();
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
    void testGetInfo() {
        assertEquals("Chequing account has $99999.75 \n" +
                "Savings account has $0.0 \n" + "RESP account has $50000.0 \n", InfoDisplayer.getInfo(b1.accounts));
        assertEquals("You have not opened any accounts.", InfoDisplayer.getInfo(b2.accounts));
    }

    @Test
    void testTransferFundsThrowException() {
        try {
            assertFalse(BankAccount.transferFunds(a2, a1, 5000)); // Requesting amount (from a2) larger than balance
        } catch (NegativeAmount | NegativeBalance | ExceedMaxAmount e) {
            // Successful
        }
        assertEquals(99999.75, a1.getBalance());
        assertEquals(0, a2.getBalance());
        try {
            assertFalse(BankAccount.transferFunds(a3, a1, 5000)); // Transferring amount (into a1) that exceeds maximum allowable balance
        } catch (NegativeAmount | NegativeBalance | ExceedMaxAmount e) {
            // Successful
        }
        try {
            assertFalse(BankAccount.transferFunds(a1, a2, -1000)); // Negative amount input
        } catch (NegativeAmount | NegativeBalance | ExceedMaxAmount e) {
            // Successful
        }
        assertEquals(50000.0, a3.getBalance());
    }

    @Test
    void testTransferFunds() {
        try {
            assertTrue(BankAccount.transferFunds(a1, a2, 1000));
        } catch (NegativeAmount | NegativeBalance | ExceedMaxAmount e) {
            fail();
        }
        assertEquals(98999.75, a1.getBalance());
        assertEquals(999.75, a2.getBalance());
    }

    @Test
    void testGetName() {
        assertEquals("Johnny Kam", b1.getName());
    }
}
