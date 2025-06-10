package ir.ac.kntu;

import java.util.Scanner;

public class SupporterHelper {
    private Supporter supporter;
    private Vendilo vendilo;

    public Supporter getSupporter() {
        return supporter;
    }

    public void setSupporter(Supporter supporter) {
        this.supporter = supporter;
    }

    public SupporterHelper(Supporter supporter, Vendilo vendilo) {
        this.supporter = supporter;
        this.vendilo = vendilo;
    }

    public void menu(Scanner scanner) {
        boolean runMenu = true;
        while (runMenu) {
            System.out.println("- - - - Supporter Menu - - - -\n1-Persons Report\n2-Orders");
            System.out.println("3-Log out\n4-Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                // case "1" -> supporter.sellersVerification(scanner, vendilo);
                case "1" -> supporter.seePersonsReport(scanner, vendilo.getCustomers(), vendilo);
                case "2" -> supporter.seeOrders(scanner, vendilo.getCustomers());
                case "3" -> runMenu = false;
                case "4" -> System.exit(0);
                default -> System.out.println("The selected option is invalid.");
            }
        }
    }
}
