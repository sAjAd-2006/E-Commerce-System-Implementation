package ir.ac.kntu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {
    private Wallet wallet;

    @BeforeEach
    public void setUp() {
        wallet = new Wallet(1000);
    }

    @Test
    public void testWithdrawSuccess() {
        assertTrue(wallet.withdrawFromWallet(500, "Shopping"));
        assertEquals(500, wallet.getBalance());
        assertEquals(1, wallet.getTransactions().size());
    }

    @Test
    public void testAddToWallet() {
        wallet.addToWallet(300);
        assertEquals(1300, wallet.getBalance());
        assertEquals(1, wallet.getTransactions().size());
    }

    // ./gradlew test --tests "ir.ac.kntu.WalletTest"
}