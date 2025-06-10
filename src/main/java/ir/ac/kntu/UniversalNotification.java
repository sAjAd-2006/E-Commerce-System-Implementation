package ir.ac.kntu;

import java.util.Scanner;

public class UniversalNotification extends Notification {
    private String message;

    public UniversalNotification(String message) {
        super(DeclarationType.public_announcement, "Message from the manager of Vandillo");
        this.message = message;
    }

    @Override
    public void interNotif(Customer customer, Scanner scanner) {
        setSeen(true);
        Color.printGreen(message);
    }
}
