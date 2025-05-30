package ir.ac.kntu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MobileTest {
    private Mobile mobile;

    @BeforeEach
    public void setUp() {
        mobile = new Mobile();
        mobile.setRearCameraResolution("48MP");
        mobile.setFrontCameraResolution("12MP");
        mobile.setInternetNetwork("5G");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("48MP", mobile.getRearCameraResolution());
        assertEquals("12MP", mobile.getFrontCameraResolution());
        assertEquals("5G", mobile.getInternetNetwork());
    }

    @Test
    public void testToString() {
        String expected = "Additional information Mobile -> Rear Camera Resolution:48MP Front Camera Resolution:12MP Internet Network:5G";
        assertTrue(mobile.toString().contains(expected));
    }

    // ./gradlew test --tests "ir.ac.kntu.MobileTest"
}