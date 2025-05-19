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

    public void seeCart() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < kalas.size(); i++) {
            System.out.println(
                    (i + 1) + ")" + " Name:" + kalas.get(i).getName() + " Product Type:"
                            + this.getClass().getSimpleName() + " Price:"
                            + kalas.get(i).getPrice());
        }
        while (true) {
            System.out.println("1) View product information\n2) Back");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewproduct();
                    break;
                default:
                    scanner.close();
                    return;
            }
        }
    }

    private void viewproduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the desired product number.");
        String choice = scanner.nextLine();
        int i = Integer.parseInt(choice) - 1;
        System.out.println("Name:" + kalas.get(i).getName() + " Product Type:" + this.getClass().getSimpleName()
                + " Price:" + kalas.get(i).getPrice());
        System.out.println("1) Remove product\n2) Back");
        choice = scanner.nextLine();
        scanner.close();
        switch (choice) {
            case "1":
                kalas.remove(i);
                return;

            default:
                return;
        }
    }
}
