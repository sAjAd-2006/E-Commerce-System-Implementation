package ir.ac.kntu;

import java.time.LocalDate;
// import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomerHelper {
    private Customer customer;
    private Vendilo vendilo;
    private Map<Customer, LocalDate> vendiloPlus;
    private CustomerHelper2 cHelper2;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerHelper() {
    }

    public CustomerHelper(Customer customer, Vendilo vendilo, Map<Customer, LocalDate> vendiloPlus) {
        this.customer = customer;
        this.vendilo = vendilo;
        this.vendiloPlus = vendiloPlus;
        this.cHelper2 = new CustomerHelper2(customer, vendiloPlus);
    }

    public void menu(Scanner scanner) {
        boolean runMenu = true;
        while (runMenu) {
            vendiloPlusRun();
            System.out.println("- - - - Customer Menu - - - -\n1-Product Search\n2-Shopping Cart\n3-Addresses");
            System.out.println(
                    "4-Wallet\n5-Discount Code\n6-Orders\n7-Vendilo Pluse\n8-Notification\n9-Settings\n10-Support");
            System.out.print("11-Log out\n12-Exit\n=> ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> customer.searchKala(scanner);
                case "2" -> menuCase2(scanner);
                case "3" -> addressRun(scanner);
                case "4" -> walletRun(scanner);
                case "5" -> discountCodeRun(scanner);
                case "6" -> ordersRun(scanner);
                case "7" -> vPlusRun(scanner);
                case "8" -> notificationRun(scanner);
                case "9" -> {
                    UserSettings userSettings = new UserSettings(vendilo.getCustomers(), vendilo.getSellers(),
                            vendilo.getSellersVerification());
                    userSettings.settings(customer, scanner);
                }
                case "10" -> supportRun(scanner);
                case "11" -> runMenu = false;
                case "12" -> ExitVendilo.exit(scanner);
                default -> System.out.println("The selected option is invalid.");
            }
        }
    }

    private void notificationRun(Scanner scanner) {
        availableKalaOrNot();
        List<Notification> availableNotif = new ArrayList<>();
        for (Notification notification : customer.getNotifications()) {
            if (notification.isCanSeeOrNot() && !notification.isSeen()) {
                availableNotif.add(notification);
            }
        }
        while (true) {
            Color.printBlue("notifications");
            Paginator<Notification> paginator = new Paginator<>(availableNotif, 10);
            int select = paginator.paginate(0);
            if (select == -1) {
                return;
            }
            availableNotif.get(select).interNotif(customer, scanner);
            availableNotif.remove(select);
        }
    }

    public void availableKalaOrNot() {
        for (Notification notification : customer.getNotifications()) {
            if (notification instanceof KalaNotification) {
                Kala notifKala = ((KalaNotification) notification).getKala();
                if (notifKala.getInventory() > 0) {
                    notification.setCanSeeOrNot(true);
                    notification.setTimeNow();
                } else {
                    notification.setCanSeeOrNot(false);
                }
            }
        }
    }

    public void vPlusRun(Scanner scanner) {
        cHelper2.vPlusRun(scanner);
    }

    private void vendiloPlusRun() {
        for (Customer customer : vendiloPlus.keySet()) {
            if (LocalDate.now().isAfter(vendiloPlus.get(customer))) {
                customer.setVendiloPlus(false);
            }
        }
    }

    public void discountCodeRun(Scanner scanner) {
        Paginator<DiscountCode> paginator = new Paginator<>(customer.getDiscountCodes(), 10);
        int select = 0;
        while (true) {
            select = paginator.paginate(select);
            if (select == -1) {
                return;
            }
            select /= 10;
        }
    }

    public void menuCase2(Scanner scanner) {
        if (customer.isVendiloPlus()) {
            customer.getShoppingCart().seeCartVendiloPlus(scanner);
        } else {
            customer.getShoppingCart().seeCart(scanner);
        }
        System.out.println("\nWould you like to continue shopping? 1>YES 2>NO(Default)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                customer.setSellers(vendilo.getSellers());
                customer.continueShopping(scanner);
                break;
            default:
                break;
        }
    }

    public void supportRun(Scanner scanner) {
        while (true) {
            System.out.print("1.New Request\n2.View Old Requests\n3.Back\n4.Exit\n => ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    newRequest(scanner);
                    break;
                case "2":
                    viewOldRequests();
                    break;
                case "3":
                    return;
                case "4":
                    ExitVendilo.exit(scanner);
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public void viewOldRequests() {
        if (customer.getReportages() == null) {
            System.out.println("\nNo report\n");
            return;
        }
        Paginator<Reportage> paginator = new Paginator<>(customer.getReportages(), 10);
        int jjjj = 0;
        while (true) {
            int iiii = paginator.paginate(jjjj);
            if (iiii == -1) {
                return;
            } else {
                Reportage reportage = customer.getReportages().get(iiii);
                System.out.println("\n" + reportage + " - - - Request status>"
                        + reportage.getCheck() + " - - - Support response>" + reportage.getAnswer());
                jjjj = iiii / 10;
            }
        }
    }

    public void newRequest(Scanner scanner) {
        Report report = Report.Settings;
        while (true) {
            System.out.println(
                    "\nEnter command \n1.Product_qualit\n2.Discrepancy_between_order_and_delivered_product\n3.Settings\n4.Not_receiving_order");
            String rep = scanner.nextLine().trim().toLowerCase();
            if (rep.matches("1|2|3|4")) {
                switch (rep) {
                    case "1" -> report = Report.Product_qualit;
                    case "2" -> report = Report.Discrepancy_between_order_and_delivered_product;
                    case "3" -> report = Report.Settings;
                    case "4" -> report = Report.Not_receiving_order;
                    default -> System.out.println("Eror");
                }
                break;
            } else {
                System.out.println("Invalid command.");
            }
        }
        System.out.println("Enter the request text: ");
        String text = scanner.nextLine();
        Reportage reportage = new Reportage(report, text);
        customer.getReportages().add(reportage);
    }

    public void ordersRun(Scanner scanner) {
        if (customer.getOrders().isEmpty()) {
            System.out.println("no order");
            return;
        }
        Paginator<Order> paginator = new Paginator<>(customer.getOrders(), 10);
        int ord = paginator.paginate(0);
        if (ord == -1) {
            return;
        } else {
            customer.getOrders().get(ord).showOrderCustomer(scanner);
        }
    }

    public void walletRun(Scanner scanner) {
        cHelper2.walletRun(scanner);
    }

    public void addressRun(Scanner scanner) {
        cHelper2.addressRun(scanner);
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
