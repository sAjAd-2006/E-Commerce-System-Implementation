package ir.ac.kntu;

import java.util.Scanner;

public class SupporterHelper {
    private Supporter supporter;

    public Supporter getSupporter() {
        return supporter;
    }

    public void setSupporter(Supporter supporter) {
        this.supporter = supporter;
    }

    public SupporterHelper(Supporter supporter) {
        this.supporter = supporter;
    }

    public void menu(Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        boolean runMenu = true;
        while (runMenu) {
            System.out.println("- - - - Supporter Menu - - - -\n1-Verification\n2-Customers Report\n3-Orders");
            System.out.println("4-Log out\n5-Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> supporter.sellersVerification(scanner);
                case "2" -> supporter.seeCustomersReport(scanner);
                case "3" -> supporter.seeOrders(scanner);
                case "4" -> runMenu = false;
                case "5" -> {
                    // scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("The selected option is invalid.");
            }
        }
        // scanner.close();
    }
}
