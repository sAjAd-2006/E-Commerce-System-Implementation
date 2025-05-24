package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Supporter extends Person {
    private static List<Seller> sellersVerification;
    private static List<Customer> customersReport;
    private String agencyName;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

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

    public Supporter(String firstname, String lastname, String agencyName, String password) {
        sellersVerification = Vendilo.getSellersVerification();
        customersReport = Vendilo.getCustomers();
        setFirstname(firstname);
        setLastname(lastname);
        setAgencyName(agencyName);
        setPassword(password);
    }

    public void seeOrders() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (runBack() == 1) {
                break;
            }
            System.out.println("Do you have a specific user in mind? 1) YES 2) NO(Default)");
            String specificEmail = "ALL";
            List<Order> orders = new ArrayList<>();
            switch (scanner.nextLine()) {
                case "1":
                    System.out.print("Get user email:");
                    specificEmail = scanner.nextLine();
                    break;
                default:
                    break;
            }
            for (Customer customer : Vendilo.getCustomers()) {
                if (specificEmail.equals("ALL") || specificEmail.equals(customer.getEmail())) {
                    for (Order order : customer.getOrders()) {
                        orders.add(order);
                    }
                }
            }
            System.out.println("Set time filter: y/n (Default:All time)");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                TimeFilterHelper<Order> timeFilterHelper = new TimeFilterHelper<>();
                orders = timeFilterHelper.filterTimesByUserInput(orders);
            }
            seeFilterOrder(orders);
        }
        scanner.close();
    }

    public void seeFilterOrder(List<Order> orders) {
        Paginator<Order> paginator = new Paginator<>(orders, 10);
        int i = 0, j = 0;
        while (true) {
            int ord = paginator.paginate(i);
            if (ord == -1) {
                return;
            } else {
                Order order = orders.get(ord);
                System.out.println(order + " Customer name: " + order.getCustomerName() + "\nSellers name: "
                        + order.getSellersNames() + "\nshipping Cost: ");
                Paginator<Kala> paginator2 = new Paginator<>(order.getKalas(), 10);
                while (true) {
                    int kal = paginator2.paginate(j);
                    if (kal == -1) {
                        break;
                    } else {
                        Kala kala = order.getKalas().get(kal);
                        System.out.println("    => " + kala + " Inventory: " + kala.getInventory());
                        j = kal/10;
                    }
                }
                i = ord/10;
            }
        }
    }

    public void sellersVerification() {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (true) {
            Paginator<Seller> paginator = new Paginator<>(sellersVerification, 10);
            int j = paginator.paginate(i);
            if (j == -1) {
                scanner.close();
                return;
            } else {
                sellersVerification.get(j).chap();
                System.out.println("Do you approve or not? 1)YES 2)NO 3)Back");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        Vendilo.getSellers().add(getSellersVerification().get(j));
                        Vendilo.getSellersVerification().remove(j);
                        break;
                    case "2":
                        System.out.print("Write the reason: ");
                        choice = scanner.nextLine();
                        getSellersVerification().get(j).setReasonForRejection(choice);
                        break;
                    case "3":
                        i = j / 10;
                        continue;
                    default:
                        System.out.println("The selected option is invalid.");
                        break;
                }
            }
        }
    }

    public void seeCustomersReport() {
        Scanner scanner = new Scanner(System.in);
        Report a = topic();
        if (a == null) {
            scanner.close();
            return;
        }
        Check b = status();
        if (b == null) {
            scanner.close();
            return;
        }
        scanner.close();
        customersReport(a, b);
    }

    public Report topic() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("What reports would you like to see?\nTopic: 1)"
                    + Report.Discrepancy_between_order_and_delivered_product + "\n2)" + Report.Not_receiving_order
                    + "\n3)" + Report.Product_qualit + "\n4)" + Report.Settings + "\n5) ALL \n6) Back\n7) Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    scanner.close();
                    return Report.Discrepancy_between_order_and_delivered_product;
                case "2":
                    scanner.close();
                    return Report.Not_receiving_order;
                case "3":
                    scanner.close();
                    return Report.Product_qualit;
                case "4":
                    scanner.close();
                    return Report.Settings;
                case "5":
                    scanner.close();
                    return Report.ALL;
                case "6":
                    scanner.close();
                    return null;
                case "7":
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public Check status() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the desired status: 1)" + Check.Closed + "\n2)" + Check.Pending + "\n3)"
                    + Check.Registered + "\n4) ALL \n5) Back \n6) Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    scanner.close();
                    return Check.Closed;
                case "2":
                    scanner.close();
                    return Check.Pending;
                case "3":
                    scanner.close();
                    return Check.Registered;
                case "4":
                    scanner.close();
                    return Check.ALL;
                case "5":
                    scanner.close();
                    return null;
                case "6":
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public void customersReport(Report a, Check b) {
        setCustomersReport(Vendilo.getCustomers());
        Scanner scanner = new Scanner(System.in);
        int i = 0, j = 0;
        Paginator<Customer> paginator = new Paginator<>(customersReport, 10);
        while (true) {
            int custom = paginator.paginate(i);
            if (custom == -1) {
                scanner.close();
                return;
            } else {
                List<Reportage> reportages = new ArrayList<>();
                for (Reportage reportage : customersReport.get(custom).getReportages()) {
                    if (a == Report.ALL || reportage.getReportTopic().equals(a)) {
                        if (b == Check.ALL || reportage.getCheck().equals(b)) {
                            reportages.add(reportage);
                        }
                    }
                }
                Paginator<Reportage> paginator2 = new Paginator<>(reportages, 10);
                while (true) {
                    int repor = paginator2.paginate(j);
                    if (repor == -1) {
                        scanner.close();
                        break;
                    } else {
                        Reportage reportage = reportages.get(repor);
                        System.out.println(reportage.toString() + " Check:" + reportage.getCheck());
                        proceed(reportage);
                        j = repor / 10;
                    }
                }
                i = custom / 10;
            }
        }
    }

    public void proceed(Reportage reportage) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to proceed?\n1)YES 2)NO 3)Exit" +
                    "\nIf it has already been dealt with, you will be automatically returned");
            String c = scanner.nextLine();
            switch (c) {
                case "1":
                    if (!reportage.getCheck().equals(Check.Closed)) {
                        boolean condition = true;
                        while (condition) {
                            System.out.println("You change the status of the request to Pending or complete it?" +
                                    "1)Change 2)Pending 3)Back 4)Exit");
                            c = scanner.nextLine();
                            switch (c) {
                                case "1":
                                    reportage.setCheck(Check.Closed);
                                    System.out.print("Write the result: ");
                                    reportage.setAnswer(scanner.nextLine());
                                    condition = false;
                                    break;
                                case "2":
                                    reportage.setCheck(Check.Pending);
                                    condition = false;
                                    break;
                                case "3":
                                    condition = false;
                                    break;
                                case "4":
                                    scanner.close();
                                    System.exit(0);
                                default:
                                    System.out.println("The selected option is invalid.");
                                    break;
                            }
                        }
                    }
                    break;
                case "2":
                    scanner.close();
                    return;
                case "3":
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }
}
