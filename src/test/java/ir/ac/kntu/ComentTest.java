package ir.ac.kntu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComentTest {

    @Test
    public void testComentConstructorAndGetters() {
        Coment coment = new Coment("John", "Great product!", 5);
        
        assertEquals("John", coment.getName());
        assertEquals("Great product!", coment.getComent());
        assertEquals(5, coment.getRate());
    }

    @Test
    public void testSetters() {
        Coment coment = new Coment();
        
        coment.setName("Alice");
        coment.setComent("Average quality");
        coment.setRate(3);
        
        assertEquals("Alice", coment.getName());
        assertEquals("Average quality", coment.getComent());
        assertEquals(3, coment.getRate());
    }

    @Test
    public void testToString() {
        Coment coment = new Coment("Bob", "Not bad", 4);
        String expected = "Coment [CustomerN1ame=Bob, coment=Not bad, rate=4]";
        
        assertEquals(expected, coment.toString());
    }

    @Test
    public void testDefaultConstructor() {
        Coment coment = new Coment();
        
        assertNull(coment.getName());
        assertNull(coment.getComent());
        assertEquals(0, coment.getRate());
    }
}