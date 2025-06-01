package ir.ac.kntu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    // private List<Kala> kalas = new ArrayList<>();
    private LinkedHashMap<Kala, Integer> kalasMap;
    private int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LinkedHashMap<Kala, Integer> getKalasMap() {
        return kalasMap;
    }

    public void setKalasMap(LinkedHashMap<Kala, Integer> kalasMap) {
        this.kalasMap = kalasMap;
    }

    // public List<Kala> getKalas() {
    // return kalas;
    // }

    // public void setKalas(List<Kala> kalas) {
    // this.kalas = kalas;
    // }

    public ShoppingCart() {
        kalasMap = new LinkedHashMap<>();
    }

    public int findTotal() {
        int total = 0;
        // for (Kala kala : kalas) {
        // total += kala.getPrice();
        // }
        for (Kala kala : kalasMap.keySet()) {
            total += kala.getPrice() * kalasMap.get(kala);
        }
        setTotalPrice(total);
        return getTotalPrice();
    }

    public void seeCart(Scanner scanner) {
        while (true) {
            if (kalasMap.keySet().isEmpty()) {
                System.out.println("Your shopping cart is empty.");
                return;
            }
            List<Kala> kalas = new ArrayList<>(kalasMap.keySet());
            Paginator<Kala> paginator = new Paginator<>(kalas, 10);
            int select = 0;
            select = paginator.paginate(select);
            if (select == -1) {
                return;
            }
            int num = kalasMap.get(kalas.get(select));
            System.out.println("    <^>" + kalas.get(select) + " Number: " + num);
            System.out.println("Remove product?\n1> YES 2> NO(Default)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("1>Reduce the number 2>Remive 3>Back(Default)");
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            if (num > 1) {
                                kalasMap.replace(kalas.get(select), num, num - 1);
                            } else {
                                kalasMap.remove(kalas.get(select));
                            }
                            break;
                        case "2":
                            kalasMap.remove(kalas.get(select));
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public int runBack(Scanner scanner) {
        while (true) {
            System.out.println("Please enter the desired option : 1)Back 2)Exit 3)Continue");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    return 1;
                case "2":
                    System.exit(0);
                case "3":
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
