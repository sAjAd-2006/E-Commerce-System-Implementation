package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SellerHelper {
    private Seller seller;

    public SellerHelper(Seller seller) {
        this.seller = seller;
    }

    public void menu(Scanner scanner) {
        while (true) {
            System.out.print("- - - - Seller menu - - - -\n1.Add New Item\n2.Wallet\n3.Orders\n4.Log out\n5.Exit\n =>");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    seller.addKala(scanner);
                    break;
                case "2":
                    walletRun(scanner);
                    break;
                case "3":
                    ordersRun();
                    break;
                case "4":
                    return;
                case "5":
                    ExitVendilo.exit(scanner);
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public void ordersRun() {
        Paginator<Order> paginator = new Paginator<>(seller.getOrders(), 10);
        int jjjj = 0;
        while (true) {
            int ord = paginator.paginate(jjjj);
            if (ord == -1) {
                return;
            } else {
                Order order = seller.getOrders().get(ord);
                System.out.println(order + " Customer email: " + order.getCustomerEmail() + " Customer address: "
                        + order.getAddress());
                jjjj = ord / 10;
            }
        }
    }

    public void walletRun(Scanner scanner) {
        while (true) {
            System.out.println(
                    "1> View balance\n2> Withdraw from wallet\n3> View previous transactions\n4> Back\n5> Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> System.out.println("balance: " + seller.getWallet().getCash());
                case "2" -> {
                    System.out.print("Enter the amount you want to Withdraw from wallet: ");
                    choice = scanner.nextLine();
                    if (isInteger(choice)) {
                        seller.getWallet().withdrawFromWallet(Integer.parseInt(choice), "Withdraw money");
                    } else {
                        System.out.println("Incorrect input. Enter a number.");
                    }
                }
                case "3" -> viewPreviousTransactions(scanner);
                case "4" -> {
                    return;
                }
                case "5" -> ExitVendilo.exit(scanner);
                default -> System.out.println("Incorrect input.");
            }
        }
    }

    public void viewPreviousTransactions(Scanner scanner) {
        List<Transaction> tran = new ArrayList<>();
        System.out.println("Set time filter ALL: y/n");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            tran = seller.getWallet().getTransactions();
        } else {
            TimeFilterHelper<Transaction> timeFilterHelper = new TimeFilterHelper<>();
            tran = timeFilterHelper.filterTimesByUserInput(seller.getWallet().getTransactions(), scanner);
        }
        Paginator<Transaction> paginator = new Paginator<>(tran, 10);
        int iiii = 0;
        while (true) {
            int result = paginator.paginate(iiii);
            if (result != -1) {
                System.out.println(seller.getWallet().getTransactions().get(result) + " ---- Price: "
                        + seller.getWallet().getTransactions().get(result).getPrice());
                iiii = result / 10;
            } else {
                return;
            }
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