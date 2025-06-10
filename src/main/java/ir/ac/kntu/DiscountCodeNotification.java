package ir.ac.kntu;

import java.util.Scanner;

public class DiscountCodeNotification extends Notification {
    private DiscountCode discountCode;

    // public DiscountCode getDiscountCode() {
    //     return discountCode;
    // }

    // public void setDiscountCode(DiscountCode discountCode) {
    //     this.discountCode = discountCode;
    // }

    public DiscountCodeNotification(DiscountCode discountCode) {
        super(DeclarationType.discount_code, "You received a discount code gift from the manager.");
        this.discountCode = discountCode;
    }

    @Override
    public void interNotif(Customer customer, Scanner scanner) {
        setSeen(true);
        System.out.println(discountCode);
    }
}
