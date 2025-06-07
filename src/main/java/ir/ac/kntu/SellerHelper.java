package ir.ac.kntu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SellerHelper {
    private Seller seller;

    public SellerHelper(Seller seller) {
        this.seller = seller;
    }

    public void menu(Scanner scanner) {
        boolean run = true;
        while (run) {
            System.out.print("- - - - Seller menu - - - -\n1.Add New Item\n2-See old item\n3.Wallet\n4.Orders" +
                    "\n5.Financial report\n6.Log out\n7.Exit\n => ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> seller.addKala(scanner);
                case "2" -> seller.seeKala(scanner);
                case "3" -> walletRun(scanner);
                case "4" -> ordersRun();
                case "5" -> reportRun();
                case "6" -> run = false;
                case "7" -> ExitVendilo.exit(scanner);
                default -> System.out.println("The selected option is invalid.");
            }
        }
    }

    private void reportRun() {
        EnhancedFinancialReportGenerator generator = new EnhancedFinancialReportGenerator(seller);
        String htmlReport = generator.generateEnhancedReport();

        try {
            Path path = Paths.get("seller_full_report.html");
            Files.write(path, htmlReport.getBytes(StandardCharsets.UTF_8));
            Color.printGreen("Report saved successfully: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving report: " + e.getMessage());
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
                Color.printYellow("\n<- - - - - - - - - >");
                System.out.println(order + "\n   Customer email: " + order.getCustomerEmail());
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
                    if (isInteger(choice) && Integer.parseInt(choice) > 0) {
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