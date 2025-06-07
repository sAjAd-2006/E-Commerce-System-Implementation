package ir.ac.kntu;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomerHelper {
    private Customer customer;
    private Vendilo vendilo;
    private Map<Customer, LocalDate> vendiloPlus;

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
    }

    public void menu(Scanner scanner) {
        boolean runMenu = true;
        while (runMenu) {
            vendiloPlusRun();
            System.out.println("- - - - Customer Menu - - - -\n1-Product Search\n2-Shopping Cart\n3-Addresses");
            System.out.println(
                    "4-Wallet\n5-Discount Code\n6-Orders\n7-Vendilo Pluse\n8-Notification\n9-Settings\n10-Support");
            System.out.println("11-Log out\n12-Exit\n=>");
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
            if (availableNotif.get(select) instanceof KalaNotification) {
                availableNotif.get(select).interNotif(customer, scanner);
                availableNotif.remove(select);
            }
        }
    }

    public void availableKalaOrNot() {
        for (Notification notification : customer.getNotifications()) {
            if (notification instanceof KalaNotification) {
                Kala notifKala = ((KalaNotification) notification).getKala();
                // for (Kala kala : Seller.getKalas()) {
                // if (kala.equals(notifKala) && kala.getInventory() > 0) {
                // notification.setCanSeeOrNot(true);
                // }
                // }
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
        while (true) {
            timeBetween();
            Color.printWhiteBold("Would you like to purchase a subscription? y/n(Default)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    byVendiloPluse(scanner);
                    break;
                default:
                    return;
            }
        }
    }

    public void byVendiloPluse(Scanner scanner) {
        Color.printWhiteBold("Which subscription are you considering?\n1> 1-Month  price:1000" +
                "\n2> 3-Month  price:2900\n3> 1-Year  price:11000\n4> Back");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                if (customer.getWallet().withdrawFromWallet(1000, "Buy a subscription")) {
                    setEndTimeVendiloPlus(1);
                }
                break;
            case "2":
                if (customer.getWallet().withdrawFromWallet(2900, "Buy a subscription")) {
                    setEndTimeVendiloPlus(2);
                }
                break;
            case "3":
                if (customer.getWallet().withdrawFromWallet(11000, "Buy a subscription")) {
                    setEndTimeVendiloPlus(3);
                }
                break;
            default:
                break;
        }
    }

    public void setEndTimeVendiloPlus(int endDate) {
        customer.setVendiloPlus(true);
        if (vendiloPlus.containsKey(customer)) {
            LocalDate localDate = vendiloPlus.get(customer);
            switch (endDate) {
                case 1 -> vendiloPlus.put(customer, localDate.plusMonths(1));
                case 2 -> vendiloPlus.put(customer, localDate.plusMonths(3));
                case 3 -> vendiloPlus.put(customer, localDate.plusYears(1));
                default -> vendiloPlus.put(customer, localDate);
            }
        } else {
            switch (endDate) {
                case 1 -> vendiloPlus.put(customer, LocalDate.now().plusMonths(1));
                case 2 -> vendiloPlus.put(customer, LocalDate.now().plusMonths(3));
                case 3 -> vendiloPlus.put(customer, LocalDate.now().plusYears(1));
                default -> vendiloPlus.put(customer, LocalDate.now());
            }
        }
    }

    public void timeBetween() {
        if (!vendiloPlus.containsKey(customer) || vendiloPlus.get(customer).isBefore(LocalDate.now())) {
            return;
        }
        LocalDate endDate = vendiloPlus.get(customer);

        Period period = Period.between(LocalDate.now(), endDate);

        System.out.println("End of subscription>  " + "Year:" + Color.GREEN_BOLD + period.getYears() + Color.RESET
                + " Month:" + Color.GREEN_BOLD + period.getMonths() + Color.RESET
                + " Day:" + Color.GREEN_BOLD + period.getDays() + Color.RESET);
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
        customer.getShoppingCart().seeCart(scanner);
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
        while (true) {
            System.out.println(
                    "1> View balance\n2> Top up wallet\n3> Withdraw from wallet\n4> View previous transactions\n5> Back\n6> Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    Color.printGreen("balance: " + customer.getWallet().getCash());
                    break;
                case "2":
                    walletRunCase23(2, scanner);
                    break;
                case "3":
                    walletRunCase23(3, scanner);
                    break;
                case "4":
                    viewPreviousTransactions(scanner);
                    break;
                case "5":
                    return;
                case "6":
                    ExitVendilo.exit(scanner);
                default:
                    break;
            }
        }
    }

    public void walletRunCase23(int casey, Scanner scanner) {
        if (casey == 2) {
            System.out.print("Enter the amount you want to top up your wallet: ");
            String choice = scanner.nextLine();
            if (isInteger(choice) && Integer.parseInt(choice) > 0) {
                customer.getWallet().addToWallet(Integer.parseInt(choice));
            } else {
                System.out.println("Incorrect input. Enter a number.\n");
            }
        } else {
            System.out.print("Enter the amount you want to Withdraw from wallet: ");
            String choice = scanner.nextLine();
            if (isInteger(choice) && Integer.parseInt(choice) > 0) {
                customer.getWallet().withdrawFromWallet(Integer.parseInt(choice), "Withdraw money");
            } else {
                System.out.println("Incorrect input. Enter a number.\n");
            }
        }
    }

    public void viewPreviousTransactions(Scanner scanner) {
        List<Transaction> tran = new ArrayList<>();
        System.out.println("Set time filter ALL: y/n");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            tran = customer.getWallet().getTransactions();
        } else {
            TimeFilterHelper<Transaction> timeFilterHelper = new TimeFilterHelper<>();
            tran = timeFilterHelper.filterTimesByUserInput(customer.getWallet().getTransactions(), scanner);
        }
        if (tran.isEmpty()) {
            System.out.println("no transaction");
            return;
        }
        Paginator<Transaction> paginator = new Paginator<>(tran, 10);
        int iiii = 0;
        while (true) {
            int result = paginator.paginate(iiii);
            if (result != -1) {
                System.out.println(customer.getWallet().getTransactions().get(result) + " ---- Price: "
                        + customer.getWallet().getTransactions().get(result).getPrice());
                iiii = result / 10;
            } else {
                return;
            }
        }
    }

    public void addressRun(Scanner scanner) {
        while (true) {
            System.out.println("1. Add Address\n2. View Current Addresses\n3. Back\n4. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addAddress(scanner);
                    break;
                case "2":
                    viewCurrentAddresses(scanner);
                    break;
                case "3":
                    return;
                case "4":
                    ExitVendilo.exit(scanner);
                    break;
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public void addAddress(Scanner scanner) {
        while (true) {
            System.out.println("Adding address>>");
            if (customer.runBack(scanner) == 1) {
                return;
            }
            String title = addAddressIf1(scanner);
            String province = addAddressIf2(scanner);
            System.out.print("Enter City: ");
            String city = scanner.nextLine();
            if (city.isEmpty()) {
                System.out.println("city is empty.");
                continue;
            }
            System.out.print("Enter Description: ");
            String description = scanner.nextLine();
            if (description.isEmpty()) {
                System.out.println("description is empty.");
                continue;
            }
            Address address = new Address(title, province, city, description);
            customer.getAddresses().add(address);
        }
    }

    public String addAddressIf1(Scanner scanner) {
        while (true) {
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            if (title.isEmpty()) {
                System.out.println("Title is empty.");
                continue;
            }
            return title;
        }
    }

    public String addAddressIf2(Scanner scanner) {
        while (true) {
            System.out.print("Enter Province: ");
            String province = scanner.nextLine();
            if (province.isEmpty()) {
                System.out.println("province is empty.");
                continue;
            }
            return province;
        }
    }

    public void viewCurrentAddresses(Scanner scanner) {
        Paginator<Address> paginator = new Paginator<>(customer.getAddresses(), 10);
        int iiii = 0;
        outerLoop: while (true) {
            int result = paginator.paginate(iiii);
            if (result == -1) {
                return;
            } else {
                while (true) {
                    System.out.println("Options: 1>Edit 2>Delete 3>Back 4>Exit");
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1" -> {
                            if (customer.getAddresses().get(result) != null) {
                                editAddress(customer.getAddresses().get(result), scanner);
                            }
                            Color.printRed("The requested address no longer exists.");
                        }
                        case "2" -> customer.getAddresses().remove(result);
                        case "3" -> {
                            iiii = result / 10;
                            continue outerLoop;
                        }
                        case "4" -> ExitVendilo.exit(scanner);
                        default -> System.out.println("The selected option is invalid.");
                    }
                }
            }
        }
    }

    public void editAddress(Address address, Scanner scanner) {
        System.out.println("Don't enter any part, it won't change.");
        if (customer.runBack(scanner) == 1) {
            return;
        }
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            address.setTitle(title);
        }
        System.out.print("Enter Province: ");
        String province = scanner.nextLine();
        if (!province.isEmpty()) {
            address.setProvince(province);
        }
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        if (!city.isEmpty()) {
            address.setCity(city);
        }
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        if (description.isEmpty()) {
            address.setDescription(description);
        }
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
