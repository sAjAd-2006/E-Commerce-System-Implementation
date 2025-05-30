package ir.ac.kntu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LaptopTest {
    private Laptop laptop;

    @BeforeEach
    public void setUp() {
        laptop = new Laptop();
        laptop.setGraphicsProcessor("NVIDIA RTX 3080");
        laptop.setBluetooth(true);
        laptop.setWebcam(true);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("NVIDIA RTX 3080", laptop.getGraphicsProcessor());
        assertTrue(laptop.isBluetooth());
        assertTrue(laptop.isWebcam());
    }

    @Test
    public void testToString() {
        String expected = "Additional information Laptop -> Graphics Processor:NVIDIA RTX 3080 Have Bluetooth:true Have Webcam:true";
        assertTrue(laptop.toString().contains(expected));
    }

    // ./gradlew test --tests "ir.ac.kntu.LaptopTest"
}