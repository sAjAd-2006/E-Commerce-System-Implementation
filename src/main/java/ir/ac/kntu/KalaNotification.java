package ir.ac.kntu;

import java.util.Scanner;

public class KalaNotification extends Notification {
    private Kala kala;

    public Kala getKala() {
        return kala;
    }

    public void setKala(Kala kala) {
        this.kala = kala;
    }

    public KalaNotification(Kala kala) {
        super(DeclarationType.product_is_available, ("Product " + kala.getName() + " is available."));
        this.kala = kala;
    }

    @Override
    public void interNotif(Customer customer, Scanner scanner) {
        setSeen(true);
        customer.displayInformationAboutTheSelectedProduct(kala, "ALL", scanner);
    }

}
