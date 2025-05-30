package ir.ac.kntu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setAuthorsName("J.K. Rowling");
        book.setNumberOfPages("400");
        book.setAgeCategory(Age.ADULT);
        book.setIdISBN("978-3-16-148410-0");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("J.K. Rowling", book.getAuthorsName());
        assertEquals("400", book.getNumberOfPages());
        assertEquals(Age.ADULT, book.getAgeCategory());
        assertEquals("978-3-16-148410-0", book.getIdISBN());
    }

    @Test
    public void testToString() {
        String expected = "Additional information Book -> Authors name:J.K. Rowling Number of pages:400 Age category:ADULT Id ISBN:978-3-16-148410-0";
        assertTrue(book.toString().contains(expected));
    }

    // ./gradlew test --tests "ir.ac.kntu.BookTest"
}