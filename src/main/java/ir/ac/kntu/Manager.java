package ir.ac.kntu;

import java.util.List;
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

    public void sendingAPublicMessage(Scanner scanner, List<Customer> customers, List<Seller> sellers) {
        System.out.println("Enter your message: ");
        String masage = scanner.nextLine();
        // UniversalNotification universNotif = new UniversalNotification(masage);
        // universNotif.setCanSeeOrNot(true);
        for (Customer customer : customers) {
            UniversalNotification universNotif = new UniversalNotification(masage);
            universNotif.setCanSeeOrNot(true);
            customer.getNotifications().add(universNotif);
        }
        for (Seller seller : sellers) {
            UniversalNotification universNotif = new UniversalNotification(masage);
            universNotif.setCanSeeOrNot(true);
            seller.getNotifications().add(universNotif);
        }
    }
}
