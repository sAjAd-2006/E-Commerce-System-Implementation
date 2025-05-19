package ir.ac.kntu;

import java.time.LocalTime;

import javax.sql.rowset.spi.SyncResolver;

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

    @Override
    public String toString() {
        return ("Time:" + getLocalTime() + " --- " + "Price:" + getPrice() + getWhy());
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }
}
