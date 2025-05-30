package ir.ac.kntu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendiloTest {
    private Vendilo vendilo;
    private Customer sampleCustomer;
    private Seller sampleSeller;
    private Supporter sampleSupporter;

    @BeforeEach
    public void setUp() {
        vendilo = new Vendilo();
        sampleCustomer = new Customer("John", "Doe", "john@example.com", "09123456789", "Password123!");
        sampleSeller = new Seller("Alice", "Smith", "Alice Store", "1234567890", "09123456788", "SellerPass123!", "Tehran");
        sampleSupporter = new Supporter("Support", "Agent", "support_agent", "SuppPass123!");
    }

    @Test
    public void testAddAndGetCustomer() {
        vendilo.getCustomers().add(sampleCustomer);
        assertEquals(1, vendilo.getCustomers().size());
        assertEquals("John", vendilo.getCustomers().get(0).getFirstname());
    }

    @Test
    public void testAddAndGetSeller() {
        vendilo.getSellers().add(sampleSeller);
        assertEquals(1, vendilo.getSellers().size());
        assertEquals("Alice Store", vendilo.getSellers().get(0).getStoreTitle());
    }

    @Test
    public void testAddAndGetSupporter() {
        vendilo.getSupporters().add(sampleSupporter);
        assertEquals(1, vendilo.getSupporters().size());
        assertEquals("support_agent", vendilo.getSupporters().get(0).getAgencyName());
    }

    @Test
    public void testSellerVerification() {
        vendilo.getSellersVerification().add(sampleSeller);
        assertEquals(1, vendilo.getSellersVerification().size());
        assertEquals("Alice Store", vendilo.getSellersVerification().get(0).getStoreTitle());
    }

    // ./gradlew test --tests "ir.ac.kntu.VendiloTest"
}