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
        while (true) {
            if (kalas.isEmpty()) {
                System.out.println("Your shopping cart is empty.");
                scanner.close();
                return;
            }
            for (int i = 0; i < kalas.size(); i++) {
                if (i % 10 == 0 && i != 0) {
                    if (runBack() == 1) {
                        scanner.close();
                        return;
                    }
                    viewproduct(i);
                    System.out.println("-------------------------\n<- Previous page | Next page ->");
                    String choicePage = scanner.nextLine();
                    boolean runPag = true;
                    while (runPag) {
                        switch (choicePage) {
                            case "<-":
                                i -= 10;
                                runPag = false;
                                break;
                            case "->":
                                runPag = false;
                                break;
                            default:
                                System.out.println("The selected option is invalid.");
                                break;
                        }
                    }
                }
                System.out.println(
                        (i + 1) + ")" + " Name:" + kalas.get(i).getName() + " Product Type:"
                                + this.getClass().getSimpleName() + " Price:"
                                + kalas.get(i).getPrice());
            }
        }
        // while (true) {
        // System.out.println("1) View product information\n2) Back");
        // String choice = scanner.nextLine();
        // switch (choice) {
        // case "1":
        // viewproduct();
        // break;
        // default:
        // scanner.close();
        // return;
        // }
        // }
    }

    private void viewproduct(int i) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the desired product number.");
            if (runBack() == 1) {
                scanner.close();
                return;
            }
            String choice = scanner.nextLine();
            if (isInteger(choice)) {
                int ran = Integer.parseInt(choice) - 1;
                if (ran >= (i - 10) && ran < i) {
                    System.out.println("Name:" + kalas.get(i).getName() + " Product Type:" +
                            this.getClass().getSimpleName() + " Price:" + kalas.get(i).getPrice());
                    System.out.println("Remove product?\n1> YES 2> NO(Default)");
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            kalas.remove(i);
                            break;

                        default:
                            break;
                    }
                } else {
                    System.out.println("Out of range input.");
                    continue;
                }
            } else {
                System.out.println("Incorrect input. Enter a number.");
                continue;
            }
        }
    }

    public int runBack() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter the desired option : 1)Back 2)Exit 3)Continue");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    scanner.close();
                    return 1;
                case "2":
                    scanner.close();
                    System.exit(0);
                case "3":
                    scanner.close();
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
