package ir.ac.kntu;

import java.time.LocalDateTime;

public class Transaction extends Timeable{
    private LocalDateTime localDateTime;
    private int price;
    private String why;

    @Override
    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    @Override
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
        setLocalDateTime(LocalDateTime.now());
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
