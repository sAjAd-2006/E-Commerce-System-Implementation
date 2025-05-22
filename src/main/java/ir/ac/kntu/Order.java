package ir.ac.kntu;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Kala> kalas;
    private LocalTime localTime;
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

    public Order(List<Kala> kalas, LocalTime localTime, List<String> sellersNames, Address address, int totalPrice,
            int shippingCost, String customerName, String customerEmail) {
        kalas = new ArrayList<>();
        sellersNames = new ArrayList<>();
        this.kalas = kalas;
        this.localTime = localTime;
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

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
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

    public void showOrder() {
        int i = 0;
        for (Kala kala : kalas) {
            i++;
            System.out.print(i + ") ");
            System.out.println(kala.toString());
        }
        System.out.println("\nTime:" + getLocalTime());
        // System.out.println("Customer name:" + );
        System.out.println("Address:" + getAddress());
    }
}
