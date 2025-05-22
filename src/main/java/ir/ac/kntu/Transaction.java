package ir.ac.kntu;

import java.time.LocalTime;

public class Transaction {
    private LocalTime localTime;
    private int price;
    private String why;

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Transaction() {
    }

    public Transaction(int price, String why) {
        this.why = why;
        this.localTime = LocalTime.now();
        this.price = price;
        // System.out.println(this.toString());
    }

    public Transaction(LocalTime localTime, int price, String why) {
        this.localTime = localTime;
        this.price = price;
        this.why = why;
    }

    @Override
    public String toString() {
        return ("Time:" + getLocalTime() + " --- " + "Price:" + getPrice() + " -> " + getWhy());
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }
}
