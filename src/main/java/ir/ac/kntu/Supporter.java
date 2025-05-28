package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Supporter extends Person {
    private static List<Seller> verification;
    private static List<Customer> customersReport;
    private String agencyName;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public static List<Seller> getSellersVerification() {
        return verification;
    }

    public static void setSellersVerification(List<Seller> verification) {
        Supporter.verification = verification;
    }

    public static List<Customer> getCustomersReport() {
        return customersReport;
    }

    public static void setCustomersReport(List<Customer> customersReport) {
        Supporter.customersReport = customersReport;
    }

    public Supporter(String firstname, String lastname, String agencyName, String password) {
        verification = new ArrayList<>();
        customersReport = new ArrayList<>();
        setFirstname(firstname);
        setLastname(lastname);
        setAgencyName(agencyName);
        setPassword(password);
    }
    

    public void seeOrders(Scanner scanner, List<Customer> customers) {
        while (true) {
            if (runBack(scanner) == 1) {
                break;
            }
            List<Order> orders = new ArrayList<>();
            orders = seeOrders2(orders, scanner, customers);
            System.out.println("Set time filter: y/n (Default:All time)");
            if ("y".equalsIgnoreCase(scanner.nextLine())) {
                TimeFilterHelper<Order> timeFilterHelper = new TimeFilterHelper<>();
                orders = timeFilterHelper.filterTimesByUserInput(orders, scanner);
            }
            seeFilterOrder(orders);
        }
    }

    public List<Order> seeOrders2(List<Order> orders, Scanner scanner, List<Customer> customers) {
        System.out.println("Do you have a specific user in mind? 1) YES 2) NO(Default)");
        String specificEmail = "ALL";
        switch (scanner.nextLine()) {
            case "1" -> {
                System.out.print("Get user email:");
                specificEmail = scanner.nextLine();
            }
            default -> System.out.println("");
        }
        for (Customer customer : customers) {
            if (specificEmail.equals(customer.getEmail()) || "ALL".equals(specificEmail)) {
                for (Order order : customer.getOrders()) {
                    orders.add(order);
                }
            }
        }
        return orders;
    }

    public void seeFilterOrder(List<Order> orders) {
        Paginator<Order> paginator = new Paginator<>(orders, 10);
        int iits = 0, jits = 0;
        while (true) {
            int ord = paginator.paginate(iits);
            if (ord == -1) {
                return;
            } else {
                Order order = orders.get(ord);
                System.out.println(order + " Customer name: " + order.getCustomerName() + "\nshipping Cost: "
                        + order.getShippingCost());
                System.out.println("Seler names:");
                for (String name : order.getSellersNames()) {
                    System.out.println(">" + name);
                }
                Paginator<Kala> paginator2 = new Paginator<>(order.getKalas(), 10);
                while (true) {
                    int kal = paginator2.paginate(jits);
                    if (kal == -1) {
                        break;
                    } else {
                        Kala kala = order.getKalas().get(kal);
                        System.out.println("    => " + kala + " Inventory: " + kala.getInventory());
                        jits = kal / 10;
                    }
                }
                iits = ord / 10;
            }
        }
    }

    public void sellersVerification(Scanner scanner, Vendilo vendilo) {
        int iiii = 0;
        while (true) {
            Paginator<Seller> paginator = new Paginator<>(verification, 10);
            int jjjj = paginator.paginate(iiii);
            if (jjjj == -1) {
                return;
            } else {
                verification.get(jjjj).chap();
                System.out.println("Do you approve or not? 1)YES 2)NO 3)Back");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1" -> case12(1, jjjj, scanner, vendilo);
                    case "2" -> case12(2, jjjj, scanner, vendilo);
                    case "3" -> {
                        iiii = jjjj / 10;
                        continue;
                    }
                    default -> System.out.println("The selected option is invalid.");
                }
            }
        }
    }

    public void case12(int casey, int jjjj, Scanner scanner, Vendilo vendilo) {
        if (casey == 1) {
            vendilo.getSellers().add(getSellersVerification().get(jjjj));
            vendilo.getSellersVerification().remove(jjjj);
        } else {
            System.out.print("Write the reason: ");
            String choice = scanner.nextLine();
            getSellersVerification().get(jjjj).setReasonForRejection(choice);
        }
    }

    public void seeCustomersReport(Scanner scanner, List<Customer> customers) {
        Report report = topic(scanner);
        if (report == null) {
            return;
        }
        Check check = status(scanner);
        if (check == null) {
            return;
        }
        setCustomersReport(customers);
        customersReport(report, check, scanner);
    }

    public Report topic(Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("What reports would you like to see?\n1)"
                    + Report.Discrepancy_between_order_and_delivered_product + "\n2)" + Report.Not_receiving_order
                    + "\n3)" + Report.Product_qualit + "\n4)" + Report.Settings + "\n5) ALL \n6) Back\n7) Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    return Report.Discrepancy_between_order_and_delivered_product;
                case "2":
                    return Report.Not_receiving_order;
                case "3":
                    return Report.Product_qualit;
                case "4":
                    return Report.Settings;
                case "5":
                    return Report.ALL;
                case "6":
                    return null;
                case "7":
                    ExitVendilo.exit(scanner);
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public Check status(Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the desired status:\n1)" + Check.Closed + "\n2)" + Check.Pending + "\n3)"
                    + Check.Registered + "\n4) ALL \n5) Back \n6) Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    return Check.Closed;
                case "2":
                    return Check.Pending;
                case "3":
                    return Check.Registered;
                case "4":
                    return Check.ALL;
                case "5":
                    return null;
                case "6":
                    ExitVendilo.exit(scanner);
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public void customersReport(Report report, Check check, Scanner scanner) {
        int iiii = 0, jjjj = 0;
        Paginator<Customer> paginator = new Paginator<>(customersReport, 10);
        while (true) {
            int custom = paginator.paginate(iiii);
            if (custom == -1) {
                return;
            } else {
                List<Reportage> reportages = new ArrayList<>();
                for (Reportage reportage : customersReport.get(custom).getReportages()) {
                    if (report == Report.ALL || reportage.getReportTopic().equals(report)) {
                        if (check == Check.ALL || reportage.getCheck().equals(check)) {
                            reportages.add(reportage);
                        }
                    }
                }
                customersReport2(reportages, jjjj, scanner);
                iiii = custom / 10;
            }
        }
    }

    public void customersReport2(List<Reportage> reportages, int jjjj, Scanner scanner) {
        Paginator<Reportage> paginator2 = new Paginator<>(reportages, 10);
        while (true) {
            int repor = paginator2.paginate(jjjj);
            if (repor == -1) {
                break;
            } else {
                Reportage reportage = reportages.get(repor);
                System.out.println(reportage.toString() + " Check:" + reportage.getCheck());
                proceed(reportage, scanner);
                jjjj = repor / 10;
            }
        }
    }

    public void proceed(Reportage reportage, Scanner scanner) {
        while (true) {
            System.out.println("Do you want to proceed?\n1)YES 2)NO 3)Exit" +
                    "\nIf it has already been dealt with, you will be automatically returned");
            String chose = scanner.nextLine();
            switch (chose) {
                case "1" -> {
                    proceedCase1(reportage, scanner);
                }
                case "2" -> {
                    return;
                }
                case "3" -> ExitVendilo.exit(scanner);
                default -> System.out.println("The selected option is invalid.");
            }
        }
    }

    public void proceedCase1(Reportage reportage, Scanner scanner) {
        if (!reportage.getCheck().equals(Check.Closed)) {
            boolean condition = true;
            while (condition) {
                System.out.println("You change the status of the request to Pending or complete it?" +
                        "1)Change 2)Pending 3)Back 4)Exit");
                String chose = scanner.nextLine();
                switch (chose) {
                    case "1" -> {
                        reportage.setCheck(Check.Closed);
                        System.out.print("Write the result: ");
                        reportage.setAnswer(scanner.nextLine());
                        condition = false;
                    }
                    case "2" -> {
                        reportage.setCheck(Check.Pending);
                        condition = false;
                    }
                    case "3" -> condition = false;
                    case "4" -> ExitVendilo.exit(scanner);
                    default -> System.out.println("The selected option is invalid.");
                }
            }
        }
    }
}
