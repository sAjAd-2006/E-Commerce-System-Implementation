package ir.ac.kntu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class TestNotification extends Notification {
    public TestNotification(DeclarationType declarationType, String abute) {
        super(declarationType, abute);
    }
}

public class NotificationTest {

    private Notification notification;
    private final LocalDate testDate = LocalDate.of(2023, 1, 1);
    private final LocalTime testTime = LocalTime.of(12, 0);

    @BeforeEach
    public void setUp() {
        notification = new TestNotification(DeclarationType.discount_code, "Test message");
    }

    @Test
    public void testConstructorInitialization() {
        assertEquals(DeclarationType.discount_code, notification.getDeclarationType());
        assertEquals("Test message", notification.getAbute());
        assertNotNull(notification.getCrDate());
        assertNotNull(notification.getCrTime());
        assertFalse(notification.isSeen());
        assertFalse(notification.isCanSeeOrNot());
    }

    @Test
    public void testGettersAndSetters() {
        notification.setDeclarationType(DeclarationType.Support_request);
        assertEquals(DeclarationType.Support_request, notification.getDeclarationType());

        notification.setAbute("New message");
        assertEquals("New message", notification.getAbute());

        notification.setCrDate(testDate);
        assertEquals(testDate, notification.getCrDate());

        notification.setCrTime(testTime);
        assertEquals(testTime, notification.getCrTime());

        notification.setSeen(true);
        assertTrue(notification.isSeen());

        notification.setCanSeeOrNot(true);
        assertTrue(notification.isCanSeeOrNot());
    }

}