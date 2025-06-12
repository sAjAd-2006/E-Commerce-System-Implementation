package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Supporter extends Person {
    private List<Seller> verification;
    private List<Customer> customersReport;
    private List<Person> personsReport = new ArrayList<>();
    private Map<Report, Integer> activityMap;
    private String agencyName;

    public Map<Report, Integer> getActivityMap() {
        return activityMap;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public List<Seller> getSellersVerification() {
        return verification;
    }

    public void setSellersVerification(List<Seller> verification) {
        this.verification = verification;
    }

    public List<Customer> getCustomersReport() {
        return customersReport;
    }

    public void setCustomersReport(List<Customer> customersReport) {
        this.customersReport = customersReport;
    }

    public Supporter(String firstname, String lastname, String agencyName, String password) {
        verification = new ArrayList<>();
        customersReport = new ArrayList<>();
        setFirstname(firstname);
        setLastname(lastname);
        setAgencyName(agencyName);
        setPassword(password);
        activityMap = new HashMap<>();
        activityMap.put(Report.ALL, 1);
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
                seeFilterOrderHelper(order, ord);
                List<Kala> kalas = new ArrayList<>(order.getKalasMap().keySet());
                Paginator<Kala> paginator2 = new Paginator<>(kalas, 10);
                while (true) {
                    int kal = paginator2.paginate(jits);
                    if (kal == -1) {
                        break;
                    } else {
                        ordKalPrint(kalas, kal, order);
                        jits = kal / 10;
                    }
                }
                iits = ord / 10;
            }
        }
    }

    public void seeFilterOrderHelper(Order order, int ord) {
        Color.printYellow("\nOrder info:");
        System.out.println(order + " Customer name: " + order.getCustomerName() + "\n   shipping Cost: "
                + order.getShippingCost());
        System.out.println("   Seler names:");
        for (String name : order.getSellersNames()) {
            System.out.println(Color.BLUE_BOLD + "   >>" + Color.RESET + name);
        }
    }

    public void ordKalPrint(List<Kala> kalas, int kal, Order order) {
        Kala kala = kalas.get(kal);
        System.out.println(Color.YELLOW_BOLD + "   => " + Color.RESET + kala + "\n      number: "
                + order.getKalasMap().get(kala) + "     Inventory: " + kala.getInventory());
    }

    // public void sellersVerification(Scanner scanner, Vendilo vendilo) {
    // int iiii = 0;
    // while (true) {
    // Paginator<Seller> paginator = new Paginator<>(verification, 10);
    // int jjjj = paginator.paginate(iiii);
    // if (jjjj == -1) {
    // return;
    // } else {
    // Color.printYellow("info>");
    // System.out.println(verification.get(jjjj).chap());
    // Color.printYellow(verification.get(jjjj).getReasonForRejection());
    // System.out.println("Do you approve or not? 1)YES 2)NO 3)Back");
    // String choice = scanner.nextLine();
    // switch (choice) {
    // case "1" -> case12(1, jjjj, scanner, vendilo);
    // case "2" -> case12(2, jjjj, scanner, vendilo);
    // case "3" -> {
    // iiii = jjjj / 10;
    // continue;
    // }
    // default -> Color.printRed("The selected option is invalid.");
    // }
    // }
    // }
    // }

    public void sellersVerificationReport(Scanner scanner, Vendilo vendilo, Seller seller) {
        Color.printYellow("info>");
        System.out.println(seller.chap());
        Color.printYellow(seller.getReasonForRejection());
        System.out.println("Do you approve or not? 1)YES 2)NO 3)Back");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> case12(1, seller, scanner, vendilo);
            case "2" -> case12(2, seller, scanner, vendilo);
            case "3" -> {
            }
            default -> Color.printRed("The selected option is invalid.");
        }
    }

    public void case12(int casey, Seller seller, Scanner scanner, Vendilo vendilo) {
        if (casey == 1) {
            vendilo.getSellers().add(seller);
            vendilo.getSellersVerification().remove(seller);
            verification.remove(seller);
        } else {
            System.out.print("Write the reason: ");
            String choice = scanner.nextLine();
            seller.setReasonForRejection(choice);
        }
    }

    public void seePersonsReport(Scanner scanner, List<Customer> customers, Vendilo vendilo) {
        // Report report = topic(scanner);
        // if (report == null) {
        // return;
        // }
        Check check = status(scanner);
        if (check == null) {
            return;
        }
        personsReport = new ArrayList<>();
        setCustomersReport(customers);
        setSellersVerification(vendilo.getSellersVerification());
        personsReport(check, scanner, vendilo);
    }

    // public Report topic(Scanner scanner) {
    // while (true) {
    // System.out.println("What reports would you like to see?\n1)"
    // + Report.Discrepancy_between_order_and_delivered_product + "\n2)" +
    // Report.Not_receiving_order
    // + "\n3)" + Report.Product_qualit + "\n4)" + Report.Settings + "\n5) ALL \n6)
    // Back\n7) Exit");
    // String choice = scanner.nextLine();
    // switch (choice) {
    // case "1":
    // return Report.Discrepancy_between_order_and_delivered_product;
    // case "2":
    // return Report.Not_receiving_order;
    // case "3":
    // return Report.Product_qualit;
    // case "4":
    // return Report.Settings;
    // case "5":
    // return Report.ALL;
    // case "6":
    // return null;
    // case "7":
    // ExitVendilo.exit(scanner);
    // default:
    // System.out.println("The selected option is invalid.");
    // break;
    // }

    // System.out.println("What reports would you like to see?");
    // for (int i = 0; i < activityMap.keySet().size(); i++) {

    // }
    // for (Report report : activityMap.keySet()) {

    // }
    // }
    // }

    public Check status(Scanner scanner) {
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

    public void personsReport(Check check, Scanner scanner, Vendilo vendilo) {
        int iiii = 0, jjjj = 0;
        for (Customer customer : customersReport) {
            if (!customer.getReportages().isEmpty()) {
                personsReport.add(customer);
            }
        }
        if (activityMap.keySet().contains(Report.Seller_authentication) ||
                activityMap.keySet().contains(Report.ALL)) {
            for (Seller seller : verification) {
                personsReport.add(seller);
            }
        }
        Paginator<Person> paginator = new Paginator<>(personsReport, 10);
        while (true) {
            int custom = paginator.paginate(iiii);
            if (custom == -1) {
                return;
            } else {
                if (personsReport.get(custom) instanceof Customer) {
                    personsReportIf(custom, jjjj, check, scanner);
                    iiii = custom / 10;
                } else {
                    sellersVerificationReport(scanner, vendilo, ((Seller) personsReport.get(custom)));
                    personsReport.remove(custom);
                }
            }
        }
    }

    private void personsReportIf(int custom, int jjjj, Check check, Scanner scanner) {
        List<Reportage> reportages = new ArrayList<>();
        for (Reportage reportage : ((Customer) personsReport.get(custom)).getReportages()) {
            if (activityMap.keySet().contains(reportage.getReportTopic()) ||
                    activityMap.keySet().contains(Report.ALL)) {
                if (check == Check.ALL || reportage.getCheck().equals(check)) {
                    reportages.add(reportage);
                }
            }
        }
        customersReport2(reportages, jjjj, scanner, ((Customer) personsReport.get(custom)));
    }

    public void customersReport2(List<Reportage> reportages, int jjjj, Scanner scanner, Customer customer) {
        Paginator<Reportage> paginator2 = new Paginator<>(reportages, 10);
        while (true) {
            int repor = paginator2.paginate(jjjj);
            if (repor == -1) {
                break;
            } else {
                Reportage reportage = reportages.get(repor);
                System.out.println(reportage.toString() + " Check:" + reportage.getCheck());
                proceed(reportages, reportage, scanner, customer);
                jjjj = repor / 10;
            }
        }
    }

    public void proceed(List<Reportage> reportages, Reportage reportage, Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("Do you want to proceed?\n1)YES 2)NO 3)Exit" +
                    "\nIf it has already been dealt with, you will be automatically returned");
            String chose = scanner.nextLine();
            switch (chose) {
                case "1" -> {
                    proceedCase1(reportages, reportage, scanner, customer);
                }
                case "2" -> {
                    return;
                }
                case "3" -> ExitVendilo.exit(scanner);
                default -> System.out.println("The selected option is invalid.");
            }
        }
    }

    public void proceedCase1(List<Reportage> reportages, Reportage reportage, Scanner scanner, Customer customer) {
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
                ReportNotification reportNotif = new ReportNotification(reportage);
                reportNotif.setCanSeeOrNot(true);
                customer.getNotifications().add(reportNotif);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "[agencyName=" + agencyName + "]";
    }

}
