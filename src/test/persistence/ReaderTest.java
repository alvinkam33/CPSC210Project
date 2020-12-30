package persistence;

import model.Account;
import model.BankAccount;
import model.RegisteredEducationSavingsPlan;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReaderTest {
    @Test
    void testParseAccountFile() {
        new Reader();
        try {
            try {
                BankAccount ba = Reader.readAccount(new File("./data/testAccount.txt"));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
