package ir.ac.kntu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountCodeTest {

    private DiscountCode fixedDiscount;
    private DiscountCode percentageDiscount;
    private DiscountCode expiredDiscount;

    @BeforeEach
    public void setUp() {
        fixedDiscount = new DiscountCode("FIXED10", Kind.Percentage, 10, 5);
        percentageDiscount = new DiscountCode("PERCENT20", Kind.Percentage, 20, 3);
        expiredDiscount = new DiscountCode("EXPIRED", Kind.Numeric, 5, 0);
    }

    @Test
    public void testFixedDiscountCalculation() {
        assertEquals(90, fixedDiscount.discountCalculation(100));
        assertEquals(4, fixedDiscount.getNumbCanBeUsed());

        assertEquals(13, fixedDiscount.discountCalculation(15));
        assertEquals(3, fixedDiscount.getNumbCanBeUsed());
    }

    @Test
    public void testPercentageDiscountCalculation() {
        assertEquals(80, percentageDiscount.discountCalculation(100));
        assertEquals(2, percentageDiscount.getNumbCanBeUsed());

        assertEquals(0, percentageDiscount.discountCalculation(0));
        assertEquals(1, percentageDiscount.getNumbCanBeUsed());
    }

    @Test
    public void testExpiredDiscountCode() {
        assertEquals(100, expiredDiscount.discountCalculation(100));
        assertEquals(0, expiredDiscount.getNumbCanBeUsed());
    }

    @Test
    public void testSetters() {
        DiscountCode code = new DiscountCode();
        
        code.setCode("NEWCODE");
        code.setKind(Kind.Percentage);
        code.setDiscountAmount(15);
        code.setNumbCanBeUsed(2);
        code.setVahed("test");

        assertEquals("NEWCODE", code.getCode());
        assertEquals(Kind.Percentage, code.getKind());
        assertEquals(15, code.getDiscountAmount());
        assertEquals(2, code.getNumbCanBeUsed());
        assertEquals("test", code.getVahed());
    }

    @Test
    public void testToString() {
        String expectedFixed = "DiscountCode [code=FIXED10, kind=Percentage, DiscountAmount=10%, NumbCanBeUsed=5]";
        assertEquals(expectedFixed, fixedDiscount.toString());

        String expectedPercentage = "DiscountCode [code=PERCENT20, kind=Percentage, DiscountAmount=20%, NumbCanBeUsed=3]";
        assertEquals(expectedPercentage, percentageDiscount.toString());
    }

    @Test
    public void testVahedAutomaticallySet() {
        DiscountCode fixed = new DiscountCode("FIXED", Kind.Numeric, 5, 1);
        assertEquals("$", fixed.getVahed());

        DiscountCode percent = new DiscountCode("PERCENT", Kind.Percentage, 10, 1);
        assertEquals("%", percent.getVahed());
    }
}