package ir.ac.kntu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SellerTest {
    private Seller seller;
    private Kala sampleKala;

    @BeforeEach
    public void setUp() {
        seller = new Seller("Ali", "Rezaei", "Ali Store", "1234567890", "09123456789", "Pass123!", "Tehran");
        sampleKala = new Book();
        sampleKala.setName("Sample Book");
        sampleKala.setPrice(10000);
    }

    @Test
    public void testAddKala() {
        seller.getSellerKala().add(sampleKala);
        assertEquals(1, seller.getSellerKala().size());
        assertEquals("Sample Book", seller.getSellerKala().get(0).getName());
    }

    @Test
    public void testWalletBalance() {
        seller.getWallet().addToWallet(5000);
        assertEquals(5000, seller.getWallet().getBalance());
    }

    @Test
    public void testIsInteger() {
        assertTrue(Seller.isInteger("123"));
        assertFalse(Seller.isInteger("abc"));
    }

    // ./gradlew test --tests "ir.ac.kntu.SellerTest"
}