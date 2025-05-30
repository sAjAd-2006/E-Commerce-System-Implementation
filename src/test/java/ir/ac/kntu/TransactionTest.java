package ir.ac.kntu;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    @Test
    public void testConstructorAndGetters() {
        LocalDateTime now = LocalDateTime.now();
        Transaction transaction = new Transaction(now, 1000, "Purchase");
        
        assertEquals(now, transaction.getLocalDateTime());
        assertEquals(1000, transaction.getPrice());
        assertEquals("Purchase", transaction.getWhy());
    }

    @Test
    public void testToString() {
        LocalDateTime time = LocalDateTime.of(2023, 1, 1, 12, 0);
        Transaction transaction = new Transaction(time, 500, "Refund");
        String expected = "Time:" + time + " -> Refund";
        assertEquals(expected, transaction.toString());
    }

    // ./gradlew test --tests "ir.ac.kntu.TransactionTest"
}