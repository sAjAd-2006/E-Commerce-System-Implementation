package ir.ac.kntu;

import java.time.LocalDateTime;

public class Transaction {
    private LocalDateTime localDateTime;
    private int price;
    private String why;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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
        this.localDateTime = LocalDateTime.now();
        this.price = price;
        // System.out.println(this.toString());
    }

    public Transaction(LocalDateTime localDateTime, int price, String why) {
        this.localDateTime = localDateTime;
        this.price = price;
        this.why = why;
    }

    @Override
    public String toString() {
        return ("Time:" + getLocalDateTime() + " -> " + getWhy());
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }
}
