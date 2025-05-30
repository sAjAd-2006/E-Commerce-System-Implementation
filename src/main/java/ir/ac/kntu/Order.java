package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order extends Timeable {
    private List<Kala> kalas;
    private List<Boolean> isVoted;
    private LocalDateTime localDateTime;
    private List<String> sellersNames;
    private String customerName;
    private String customerEmail;
    private Address address;
    private int totalPrice;
    private int shippingCost;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(int shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Order() {
        kalas = new ArrayList<>();
        sellersNames = new ArrayList<>();
    }

    public Order(List<Kala> kalas, LocalDateTime localDateTime, List<String> sellersNames, Address address,
            int totalPrice,
            int shippingCost, String customerName, String customerEmail) {
        kalas = new ArrayList<>();
        sellersNames = new ArrayList<>();
        this.kalas = kalas;
        setLocalDateTime(localDateTime);
        this.sellersNames = sellersNames;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shippingCost = shippingCost;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public List<Kala> getKalas() {
        return kalas;
    }

    public void setKalas(List<Kala> kalas) {
        this.kalas = kalas;
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    @Override
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public List<String> getSellersNames() {
        return sellersNames;
    }

    public void setSellersNames(List<String> sellersNames) {
        this.sellersNames = sellersNames;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void showOrderCustomer(Scanner scanner) {
        for (int i = 0; i < kalas.size(); i++) {
            System.out.println(i + ") " + kalas.get(i) + "\n  ^ " + kalas.get(i).getSelerName());
        }
        System.out.println("\nTime: " + getLocalDateTime() + "\nAddress: " + getAddress());
        System.out.println("Do you want to raise the goods? 1>YES 2>NO(Default)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                while (true) {
                    System.out.println("Choose");
                    Paginator<Kala> paginator = new Paginator<>(kalas, 10);
                    int rat = paginator.paginate(0);
                    if (rat == -1) {
                        return;
                    }
                    if (!this.isVoted.get(rat) && (showOrderCustomerLope2(rat, scanner) == 1)) {
                        continue;
                    } else {
                        System.out.println("You have already voted for this product.");
                        continue;
                    }
                }
            default:
                break;
        }
    }

    public int showOrderCustomerLope2(int rat, Scanner scanner) {
        while (true) {
            System.out.print("\nWhat is your rating? (1 / 2 / 3 / 4 / 5): ");
            String cmd = scanner.nextLine().trim();
            if (cmd.matches("1|2|3|4|5")) {
                this.kalas.get(rat).calculatingTheAverage(Integer.parseInt(cmd));
                this.isVoted.set(rat, true);
                return 1;
            } else {
                System.out.println("Invalid rat.");
            }
        }
    }

    @Override
    public String toString() {
        return ("Time: " + getLocalDateTime() + " Address: " + getAddress() + " Total Price: " + getTotalPrice());
    }

    public List<Boolean> getIsVoted() {
        return isVoted;
    }

    public void setIsVoted(List<Boolean> isVoted) {
        this.isVoted = isVoted;
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
