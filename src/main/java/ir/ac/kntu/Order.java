package ir.ac.kntu;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Kala> kalas;
    private LocalTime localTime;
    private List<String> sellersNames;
    private Address address;

    public Order() {
        kalas = new ArrayList<>();
        sellersNames = new ArrayList<>();
    }

    public Order(List<Kala> kalas, LocalTime localTime, List<String> sellersNames, Address address) {
        kalas = new ArrayList<>();
        sellersNames = new ArrayList<>();
        this.kalas = kalas;
        this.localTime = localTime;
        this.sellersNames = sellersNames;
        this.address = address;
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
        System.out.println("Address:" + getAddress());
    }
}
