package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SellerHelper {
    private Seller seller;

    public SellerHelper(Seller seller) {
        this.seller = seller;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Add New Item\n2.Wallet\n3.Orders\n4.Back\n5.Exit\n =>");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                seller.addKala();
                break;
            case "2":
                walletRun();
                break;
            case "3":
                ordersRun();
                break;
            case "4":
                scanner.close();
                return;
            case "5":
                scanner.close();
                System.exit(0);
            default:
                System.out.println("The selected option is invalid.");
                break;
        }
    }

    public void ordersRun() {
        Paginator<Order> paginator = new Paginator<>(seller.getOrders(), 10);
        int j = 0;
        while (true) {
            int ord = paginator.paginate(j);
            if (ord == -1) {
                return;
            } else {
                Order order = seller.getOrders().get(ord);
                System.out.println(order + " Customer email: " + order.getCustomerEmail() + " Customer address: "
                        + order.getAddress());
                j = ord / 10;
            }
        }
    }

    public void walletRun() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "1> View balance\n3> Withdraw from wallet\n4> View previous transactions\n5> Back\n6> Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("balance: " + seller.getWallet().getCash());
                    break;
                case "3":
                    System.out.print("Enter the amount you want to Withdraw from wallet: ");
                    choice = scanner.nextLine();
                    if (isInteger(choice)) {
                        seller.getWallet().WithdrawFromWallet(Integer.parseInt(choice), "Withdraw money");
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
        tran = transactionFilterHelper.filterTransactionsByUserInput(seller.getWallet().getTransactions());
        Paginator<Transaction> paginator = new Paginator<>(tran, 10);
        int i = 0;
        while (true) {
            int result = paginator.paginate(i);
            if (result != -1) {
                System.out.println(seller.getWallet().getTransactions().get(result) + " ---- Price: "
                        + seller.getWallet().getTransactions().get(result).getPrice());
                i = result / 10;
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