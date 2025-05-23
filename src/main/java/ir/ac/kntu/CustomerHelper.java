package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerHelper {
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerHelper() {
    }

    public CustomerHelper(Customer customer) {
        this.customer = customer;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean runMenu = true;
        while (runMenu) {
            System.out.println("- - - - Customer Menu - - - -\n1-Product Search\n2-Shopping Cart\n3-Addresses");
            System.out.println("4-Wallet\n5-Orders\n6-Settings\n7-Support\n8-Log out\n9-Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> customer.searchKala();
                case "2" -> {
                    customer.getShoppingCart().seeCart();
                    System.out.println("Would you like to continue shopping? 1>YES 2>NO(Default)");
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            customer.continueShopping();
                            break;
                        default:
                            break;
                    }
                }
                case "3" -> addressRun();
                case "4" -> walletRun();
                case "5" -> ordersRun();
                case "6" -> {
                    UserSettings userSettings = new UserSettings();
                    userSettings.settings(customer);
                }
                case "7" -> supportRun();
                case "8" -> runMenu = false;
                case "9" -> {
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("The selected option is invalid.");
            }
        }
        scanner.close();
    }

    public void supportRun() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.New Request\n2.View Old Requests\n3.Back\n4.Exit\n => ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    newRequest();
                    break;
                case "2":
                    viewOldRequests();
                    break;
                case "3":
                    scanner.close();
                    return;
                case "4":
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public void viewOldRequests() {
        Paginator<Reportage> paginator = new Paginator<>(customer.getReportages(), 10);
        int j = 0;
        while (true) {
            int i = paginator.paginate(j);
            if (i == -1) {
                return;
            } else {
                Reportage reportage = customer.getReportages().get(i);
                System.out.println(reportage + " Request status: " + reportage.getCheck() + " Support response: "
                        + reportage.getAnswer());
                j = i / 10;
            }
        }
    }

    public void newRequest() {
        Scanner scanner = new Scanner(System.in);
        Report report = Report.Settings;
        while (true) {
            System.out.print(
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
        scanner.close();
    }

    public void ordersRun() {
        Paginator<Order> paginator = new Paginator<>(customer.getOrders(), 10);
        int ord = paginator.paginate(0);
        if (ord == -1) {
            return;
        } else {
            customer.getOrders().get(ord).showOrderCustomer();
        }
    }

    public void walletRun() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "1> View balance\n2> Top up wallet\n3> Withdraw from wallet\n4> View previous transactions\n5> Back\n6> Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("balance: " + customer.getWallet().getCash());
                    break;
                case "2":
                    System.out.print("Enter the amount you want to top up your wallet: ");
                    choice = scanner.nextLine();
                    if (isInteger(choice)) {
                        customer.getWallet().addToWallet(Integer.parseInt(choice));;
                    } else {
                        System.out.println("Incorrect input. Enter a number.");
                    }
                    break;
                case "3":
                    System.out.print("Enter the amount you want to Withdraw from wallet: ");
                    choice = scanner.nextLine();
                    if (isInteger(choice)) {
                        customer.getWallet().WithdrawFromWallet(Integer.parseInt(choice), "Withdraw money");
                    } else {
                        System.out.println("Incorrect input. Enter a number.");
                    }
                    break;
                case "4":
                    viewPreviousTransactions();
                    break;
                case "5":
                    scanner.close();
                    return;
                case "6":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    public void viewPreviousTransactions() {
        List<Transaction> tran = new ArrayList<>();
        TransactionFilterHelper transactionFilterHelper = new TransactionFilterHelper();
        tran = transactionFilterHelper.filterTransactionsByUserInput(customer.getWallet().getTransactions());
        Paginator<Transaction> paginator = new Paginator<>(tran, 10);
        int i = 0;
        while (true) {
            int result = paginator.paginate(i);
            if (result != -1) {
                System.out.println(customer.getWallet().getTransactions().get(result) + " ---- Price: "
                        + customer.getWallet().getTransactions().get(result).getPrice());
                i = result / 10;
            } else {
                return;
            }
        }
    }

    public void addressRun() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Address\n2. View Current Addresses\n3. Back\n4. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addAddress();
                    break;
                case "2":
                    viewCurrentAddresses();
                    break;
                case "3":
                    scanner.close();
                    return;
                case "4":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public void addAddress() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (customer.runBack() == 1) {
                scanner.close();
                return;
            }
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            if (title.isEmpty()) {
                System.out.println("Title is empty.");
                continue;
            }
            System.out.print("Enter Province: ");
            String province = scanner.nextLine();
            if (province.isEmpty()) {
                System.out.println("province is empty.");
                continue;
            }
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
            scanner.close();
        }
    }

    public void viewCurrentAddresses() {
        Scanner scanner = new Scanner(System.in);
        Paginator<Address> paginator = new Paginator<>(customer.getAddresses(), 10);
        int i = 0;
        outerLoop: while (true) {
            int result = paginator.paginate(i);
            if (result == -1) {
                scanner.close();
                return;
            } else {
                while (true) {
                    System.out.println("Options: 1>Edit 2>Delete 3>Back 4>Exit");
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            editAddress(customer.getAddresses().get(result));
                            break;
                        case "2":
                            customer.getAddresses().remove(result);
                            break;
                        case "3":
                            i = result / 10;
                            continue outerLoop;
                        case "4":
                            scanner.close();
                            System.exit(0);
                        default:
                            System.out.println("The selected option is invalid.");
                            break;
                    }
                }
            }
        }
    }

    public void editAddress(Address address) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Don't enter any part, it won't change.");
        if (customer.runBack() == 1) {
            scanner.close();
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
        scanner.close();
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
