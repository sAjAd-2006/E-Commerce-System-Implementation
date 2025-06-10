package ir.ac.kntu;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomerHelper2 {
    private Customer customer;
    // private Vendilo vendilo;
    private Map<Customer, LocalDate> vendiloPlus;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerHelper2() {
    }

    public CustomerHelper2(Customer customer, Map<Customer, LocalDate> vendiloPlus) {
        this.customer = customer;
        // this.vendilo = vendilo;
        this.vendiloPlus = vendiloPlus;
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

    private void byVendiloPluse(Scanner scanner) {
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

    private void setEndTimeVendiloPlus(int endDate) {
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

    private void timeBetween() {
        if (!vendiloPlus.containsKey(customer) || vendiloPlus.get(customer).isBefore(LocalDate.now())) {
            return;
        }
        LocalDate endDate = vendiloPlus.get(customer);

        Period period = Period.between(LocalDate.now(), endDate);

        System.out.println("End of subscription>  " + "Year:" + Color.GREEN_BOLD + period.getYears() + Color.RESET
                + " Month:" + Color.GREEN_BOLD + period.getMonths() + Color.RESET
                + " Day:" + Color.GREEN_BOLD + period.getDays() + Color.RESET);
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

    private void walletRunCase23(int casey, Scanner scanner) {
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

    private void viewPreviousTransactions(Scanner scanner) {
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
        boolean run = true;
        while (run) {
            System.out.println("1. Add Address\n2. View Current Addresses\n3. Back\n4. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addAddress(scanner);
                case "2" -> viewCurrentAddresses(scanner);
                case "3" -> run = false;
                case "4" -> ExitVendilo.exit(scanner);
                default -> System.out.println("The selected option is invalid.");
            }
        }
    }

    private void addAddress(Scanner scanner) {
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

    private String addAddressIf1(Scanner scanner) {
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

    private String addAddressIf2(Scanner scanner) {
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

    private void viewCurrentAddresses(Scanner scanner) {
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

    private void editAddress(Address address, Scanner scanner) {
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

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
