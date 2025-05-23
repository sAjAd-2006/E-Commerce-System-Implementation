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
                case "6" -> {UserSettings userSettings = new UserSettings();
                userSettings.settings(customer);}
                
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
            System.out.println("1> View balance\n2> Top up wallet\n3> View previous transactions\n4> Back\n5> Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("balance: " + customer.getWallet().getCash());
                    break;
                case "2":
                    System.out.print("Enter the amount you want to top up your wallet: ");
                    choice = scanner.nextLine();
                    if (isInteger(choice)) {
                        customer.getWallet().setCash(customer.getWallet().getCash() + Integer.parseInt(choice));
                    } else {
                        System.out.println("Incorrect input. Enter a number.");
                    }
                    break;
                case "3":
                    viewPreviousTransactions();
                    break;
                case "4":
                    scanner.close();
                    return;
                case "5":
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
        // System.out.println("Enter the time period:\n");
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
