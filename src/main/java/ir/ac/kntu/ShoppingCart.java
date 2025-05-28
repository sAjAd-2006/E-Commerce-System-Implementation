package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    private List<Kala> kalas = new ArrayList<>();
    private int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Kala> getKalas() {
        return kalas;
    }

    public void setKalas(List<Kala> kalas) {
        this.kalas = kalas;
    }

    public int findTotal() {
        int total = 0;
        for (Kala kala : kalas) {
            total += kala.getPrice();
        }
        setTotalPrice(total);
        return getTotalPrice();
    }

    public void seeCart(Scanner scanner) {
        while (true) {
            if (kalas.isEmpty()) {
                System.out.println("Your shopping cart is empty.");
                return;
            }
            Paginator<Kala> paginator = new Paginator<>(kalas, 10);
            int select = 0;
            select = paginator.paginate(select);
            if (select == -1) {
                return;
            }
            System.out.println("    <^>" + kalas.get(select));
            System.out.println("Remove product?\n1> YES 2> NO(Default)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    kalas.remove(select);
                    break;
                default:
                    break;
            }
        }
    }

    public int runBack(Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter the desired option : 1)Back 2)Exit 3)Continue");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // scanner.close();
                    return 1;
                case "2":
                    // scanner.close();
                    System.exit(0);
                case "3":
                    // scanner.close();
                    return 3;
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
