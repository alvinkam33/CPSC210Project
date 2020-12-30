package model;

import exceptions.ExceedMaxAmount;
import exceptions.NegativeAmount;
import exceptions.NegativeBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Account.STARTING_BALANCE;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    Chequing a1;
    Savings a2;
    RegisteredEducationSavingsPlan a3;

    @BeforeEach
    void runBefore() {
        a1 = new Chequing("123456", STARTING_BALANCE);
        a2 = new Savings("987654", STARTING_BALANCE);
        a3 = new RegisteredEducationSavingsPlan("192837", "Alvin Kam", 50000);
        try {
            a1.deposit(100000);
        } catch (NegativeAmount | ExceedMaxAmount e) {
            fail();
        }
    }

    @Test
    void testDepositThrowException() {
        try {
            assertFalse(a1.deposit(1000)); // Exceeds maximum allowable balance
        } catch (NegativeAmount | ExceedMaxAmount e) {
            // Successful
        }
        try {
            assertFalse(a1.deposit(-1000)); // Negative amount input
        } catch (NegativeAmount | ExceedMaxAmount e) {
            // Successful
        }
        assertEquals(99999.75, a1.getBalance());
    }

    @Test
    void testDeposit() {
        try {
            assertTrue(a2.deposit(5000));
        } catch (NegativeAmount | ExceedMaxAmount e) {
            fail();
        }
        assertEquals(4999.75, a2.getBalance());
    }

    @Test
    void testWithdrawThrowException() {
        try {
            assertFalse(a2.withdraw(1000)); // Requesting for amount larger than balance
        } catch (NegativeBalance | NegativeAmount e) {
            // Successful
        }
        assertEquals(0, a2.getBalance());
        try {
            assertFalse(a1.withdraw(-1000)); // Negative amount input
        } catch (NegativeBalance | NegativeAmount e) {
            // Successful
        }
    }

    @Test
    void testWithdraw() {
        try {
            assertTrue(a1.withdraw(1000));
        } catch (NegativeBalance | NegativeAmount e) {
            fail();
        }
        assertEquals(98999.50, a1.getBalance());
        try {
            assertFalse(a3.withdraw(5000, "Kevin Kam")); // Child's name is wrong
        } catch (NegativeBalance | NegativeAmount e) {
            fail();
        }
        assertEquals(50000.0, a3.getBalance());
        try {
            assertTrue(a3.withdraw(5000, "Alvin Kam"));
        } catch (NegativeBalance | NegativeAmount e) {
            fail();
        }
        assertEquals(44999.75, a3.getBalance());
    }


    @Test
    void testChangePin() {
        a1.changePin("242424");
        assertEquals("242424", a1.getPin());
    }

    @Test
    void testWriteChequeThrowException() {
        try {
            assertFalse(a1.writeCheque(150000)); // Requesting for amount larger than balance
        } catch (NegativeBalance | NegativeAmount e) {
            // Successful
        }
        try {
            assertFalse(a1.writeCheque(-1000)); // Negative amount input
        } catch (NegativeBalance | NegativeAmount e) {
            // Successful
        }
        assertEquals(99999.75, a1.getBalance());
    }

    @Test
    void testWriteCheque() {
        try {
            assertTrue(a1.writeCheque(50000));
        } catch (NegativeBalance | NegativeAmount e) {
            fail();
        }
        assertEquals(49999.50, a1.getBalance());
    }

    @Test
    void testGetInterestRate() {
        assertEquals("3.0%", a2.getInterestRate());
    }

    @Test
    void testGetChildName() {
        assertEquals("Alvin Kam", a3.getChildName());
    }

    @Test
    void testInfo() {
        assertEquals("Chequing account has $99999.75", InfoDisplayer.chequingInfo(a1));
        try {
            a1.withdraw(50000);
        } catch (NegativeAmount | NegativeBalance e) {
            fail();
        }
        assertEquals("Chequing account has $49999.5", InfoDisplayer.chequingInfo(a1));
        assertEquals("Savings account has $0.0", InfoDisplayer.savingsInfo(a2));
        try {
            a2.deposit(10000);
        } catch (NegativeAmount | ExceedMaxAmount e) {
            fail();
        }
        assertEquals("Savings account has $9999.75", InfoDisplayer.savingsInfo(a2));
        assertEquals("RESP account has $50000.0", InfoDisplayer.respInfo(a3));
    }
}
