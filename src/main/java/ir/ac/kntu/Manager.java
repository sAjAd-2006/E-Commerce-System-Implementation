package ir.ac.kntu;

import java.util.Scanner;

public class Manager extends Person {
    private String agencyName;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Manager(String firstname, String lastname, String agencyName, String password, int accessLevel) {
        setFirstname(firstname);
        setLastname(lastname);
        this.agencyName = agencyName;
        setPassword(password);
        setAccessLevel(accessLevel);
    }

    public void sendingAPublicMessage(Scanner scanner, Vendilo vendilo) {
        System.out.println("Enter your message: ");
        UniversalNotification universNotif = new UniversalNotification(scanner.nextLine());
        for (Customer customer : vendilo.getCustomers()) {
            customer.getNotifications().add(universNotif);
        }
        for (Seller seller : vendilo.getSellers()) {
            seller.getNotifications().add(universNotif);
        }
    }
}
