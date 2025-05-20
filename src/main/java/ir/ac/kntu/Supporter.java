package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Supporter extends Person {
    private static List<Seller> sellersVerification = new ArrayList<>();
    private static List<Customer> customersReport = new ArrayList<>();

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

    public void seeOrders() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you have a specific user in mind? 1) YES 2) NO");
        String specificEmail;
        switch (scanner.nextInt()) {
            case 1:
                System.out.print("Get user email:");
                scanner.nextLine();
                specificEmail = scanner.nextLine();
                break;

            default:
                specificEmail = "ALL";
                break;
        }
        int j = 0, k = 0, s = 0;
        for (Customer customer : Vendilo.getCustomers()) {
            j++;
            System.out.println(j + ") Customer name: " + customer.getFirstname() + " " + customer.getLastname());
            for (Order order : customer.getOrders()) {
                k++;
                System.out
                        .println(j + "-" + k + ") Order ->\n" + "Total Price: " + order.getTotalPrice()
                                + " Shipping Cost:" + order.getShippingCost());
                for (Kala kala : order.getKalas()) {
                    s++;
                    System.out.println(j + "-" + k + "-" + s + ") Kala name:" + kala.getName() + " Seler Name:"
                            + kala.getSelerName());
                }
                System.out.println(order.getAddress());
            }
        }
        System.out.print("Enter the buyer, order and product number: ");
        j = scanner.nextInt();
        k = scanner.nextInt();
        s = scanner.nextInt();
        System.out.println(Vendilo.getCustomers().get(j).getOrders().get(k).getKalas().get(s).toString());
    }

    public String filterOrder() {
        /*
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         */
        return null;
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
                Vendilo.getSellersVerification().remove(i);
                Vendilo.getSellers().add(getSellersVerification().get(i));
                break;

            default:
                System.out.print("Write the reason: ");
                choice = scanner.nextLine();
                getSellersVerification().get(i).setReasonForRejection(choice);
                break;
        }
    }

    public void seeCustomersReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What reports would you like to see?\nTopic: 1)"
                + Report.Discrepancy_between_order_and_delivered_product + " 2)" + Report.Not_receiving_order + " 3)"
                + Report.Product_qualit + " 4)" + Report.Settings + " 5) ALL");
        int i = scanner.nextInt();
        Report a;
        switch (i) {
            case 1:
                a = Report.Discrepancy_between_order_and_delivered_product;
                break;
            case 2:
                a = Report.Not_receiving_order;
                break;
            case 3:
                a = Report.Product_qualit;
                break;
            case 4:
                a = Report.Settings;
                break;
            default:
                a = null;
                break;
        }
        System.out.println("Enter the desired status: 1)" + Check.Closed + " 2)" + Check.Pending + " 3)"
                + Check.Registered + " 4) ALL");
        int j = scanner.nextInt();
        Check b;
        switch (i) {
            case 1:
                b = Check.Closed;
                break;
            case 2:
                b = Check.Pending;
                break;
            case 3:
                b = Check.Registered;
                break;
            default:
                b = null;
                break;
        }
        scanner.nextLine();
        scanner.close();
        customersReport(a, b);
    }

    public void customersReport(Report a, Check b) {
        Scanner scanner = new Scanner(System.in);
        int i = 0, j = 0;
        for (Customer customer : customersReport) {
            j++;
            for (Reportage reportage : customer.getReportages()) {
                i++;
                if (a == null || reportage.getReportTopic().equals(a)) {
                    if (b == null || reportage.getCheck().equals(b)) {
                        System.out.println(j + "-" + i + ") " + "Email:" + customer.getEmail() + " Topic:"
                                + reportage.getReportTopic() + " Check:" + reportage.getCheck());
                    }
                }
            }
        }
        System.out.print("Enter the number and subnumber: ");
        j = scanner.nextInt();
        i = scanner.nextInt();
        scanner.nextLine();
        System.out.println(customersReport.get(j).getReportages().get(i).toString());
        System.out.println(
                "Do you want to proceed?\n1)YES 2)NO\nIf it has already been dealt with, you will be automatically returned");
        int c = scanner.nextInt();
        switch (c) {
            case 1:
                if (!customersReport.get(j).getReportages().get(i).getCheck().equals(Check.Closed)) {
                    System.out.println(
                            "You change the status of the request to Pending or complete it? 1)Change 2)Pending");
                    c = scanner.nextInt();
                    switch (c) {
                        case 1:
                            customersReport.get(j).getReportages().get(i).setCheck(Check.Closed);
                            System.out.print("Write the result: ");
                            scanner.nextLine();
                            customersReport.get(j).getReportages().get(i).setAnswer(scanner.nextLine());
                            scanner.close();
                            break;

                        default:
                            customersReport.get(j).getReportages().get(i).setCheck(Check.Pending);
                            scanner.close();
                            break;
                    }
                }
                break;

            default:
                scanner.close();
                break;
        }
    }
}
