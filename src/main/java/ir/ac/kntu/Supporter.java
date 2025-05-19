package ir.ac.kntu;

import java.util.List;
import java.util.Scanner;

public class Supporter extends Person {
    private static List<Seller> sellersVerification;
    private static List<Customer> customersReport;

    public static List<Seller> getSellersVerification() {
        return sellersVerification;
    }

    public static void setSellersVerification(List<Seller> sellersVerification) {
        Supporter.sellersVerification = sellersVerification;
    }

    public static List<Customer> getCustomersReport() {
        return customersReport;
    }

    public static void setCustomersReport(List<Customer> customersReport) {
        Supporter.customersReport = customersReport;
    }

    public void sellersVerification() {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        for (Seller seller : sellersVerification) {
            i++;
            System.out.println(i + ") " + seller.getStoreTitle());
        }
        System.out.print("Selected item: ");
        String choice = scanner.nextLine();
        i = Integer.parseInt(choice) - 1;
        System.out.println(getSellersVerification().get(i).toString());
        System.out.println("Do you approve or not? 1)YES 2)NO");
        choice = scanner.nextLine();
        switch (choice) {
            case "1":
                Vendilo.getSellers().add(getSellersVerification().get(i));
                break;
        
            default:
                System.out.print("Write the reason: ");
                choice = scanner.nextLine();
                getSellersVerification().get(i).setReasonForRejection(choice);
                break;
        }
    }

    public void customersReport() {
        int i = 0 , j = 0;
        for (Customer customer : customersReport) {
            j++;
            for (Reportage reportage : customer.getReportages()) {
                i++;
                System.out.println(j + "-" + i + ") " + reportage.toString());
            }
        }
        /*
         * 
         * 
         * 
         * 
         * 
         * 
         */
    }
}
