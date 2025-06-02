package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Order extends Timeable {
    // private List<Kala> kalas;
    private Map<Kala, Integer> kalasMap;
    private Map<Kala, Boolean> kalasVoteMap;
    // private List<Boolean> isVoted;
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

    public Map<Kala, Integer> getKalasMap() {
        return kalasMap;
    }

    public void setKalasMap(Map<Kala, Integer> kalasMap) {
        this.kalasMap = kalasMap;
    }

    public Map<Kala, Boolean> getKalasVoteMap() {
        return kalasVoteMap;
    }

    public void setKalasVoteMap(Map<Kala, Boolean> kalasVoteMap) {
        this.kalasVoteMap = kalasVoteMap;
    }

    public Order() {
        // kalas = new ArrayList<>();
        kalasMap = new LinkedHashMap<>();
        kalasVoteMap = new LinkedHashMap<>();
        sellersNames = new ArrayList<>();
        // isVoted = new ArrayList<>();
        address = new Address();
    }

    public Order(Map<Kala, Integer> kalasMap, LocalDateTime localDateTime, List<String> sellersNames,
            Address address,
            int totalPrice,
            int shippingCost, String customerName, String customerEmail) {
        // kalas = new ArrayList<>();
        // sellersNames = new ArrayList<>();
        // isVoted = new ArrayList<>();
        // address = new Address();
        // this.kalas = kalas;
        // for (Kala kala : kalas) {
        // if (kalasMap.containsKey(kala)) {
        // int oldNum = kalasMap.get(kala);
        // kalasMap.replace(kala, oldNum, oldNum + 1);
        // } else {
        // kalasMap.put(kala, 1);
        // kalasVoteMap.put(kala, false);
        // }
        // }
        // kalasMap = new LinkedHashMap<>();
        // kalasVoteMap = new LinkedHashMap<>();
        this.kalasMap = kalasMap;
        setLocalDateTime(localDateTime);
        this.sellersNames = sellersNames;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shippingCost = shippingCost;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    // public List<Kala> getKalas() {
    // return kalas;
    // }

    // public void setKalas(List<Kala> kalas) {
    // this.kalas = kalas;
    // }

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
        int index = 0;
        for (Kala kala : kalasMap.keySet()) {
            index++;
            System.out.println(index + ") " + kala + "\n        Number: " + kalasMap.get(kala));
        }
        System.out.println("\n      Time: " + getLocalDateTime() + "\n      Address: " + getAddress());
        System.out.println("Do you want to raise the goods? 1>YES 2>NO(Default)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                List<Kala> kalas = new ArrayList<>(kalasMap.keySet());
                while (true) {
                    Paginator<Kala> paginator = new Paginator<>(kalas, 10);
                    int rat = paginator.paginate(0);
                    if (rat == -1) {
                        return;
                    }
                    if (!kalasVoteMap.get(kalas.get(rat)) && (showOrderCustomerLope2(kalas.get(rat), scanner) == 1)) {
                        continue;
                    } else {
                        Color.printRed("\nYou have already voted for this product.");
                        continue;
                    }
                }
            default:
                break;
        }
    }

    public int showOrderCustomerLope2(Kala kala, Scanner scanner) {
        while (true) {
            System.out.print("\nWhat is your rating? (1 / 2 / 3 / 4 / 5): ");
            String cmd = scanner.nextLine().trim();

            if (cmd.matches("1|2|3|4|5")) {
                kala.calculatingTheAverage(Integer.parseInt(cmd));
                // this.isVoted.set(rat, true);
                kalasVoteMap.replace(kala, false, true);
                return 1;
            } else {
                System.out.println("Invalid rat.");
            }
        }
    }

    @Override
    public String toString() {
        return ("Time: " + getLocalDateTime() + "\n   Address: " + getAddress() + "\n   Total Price: " + getTotalPrice()
                + " ShippingCost: " + getShippingCost());
    }

    // public List<Boolean> getIsVoted() {
    // return isVoted;
    // }

    // public void setIsVoted(List<Boolean> isVoted) {
    // this.isVoted = isVoted;
    // }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
