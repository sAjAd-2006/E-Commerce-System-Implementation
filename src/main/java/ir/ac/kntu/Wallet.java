package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private int cash;
    private List<Transaction> transactions;

    public int getCash() {
        return cash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Wallet() {
        transactions = new ArrayList<>();
    }

    public Wallet(int cash) {
        this.cash = cash;
        transactions = new ArrayList<>();
    }

    public boolean withdrawFromWallet(int draw, String why) {
        if (this.cash - draw >= 0) {
            this.cash -= draw;
            Transaction transaction = new Transaction(draw, why);
            transactions.add(transaction);
            return true;
        } else {
            System.out.println("The amount requested is greater than your account balance.");
            return false;
        }
    }

    public void addToWallet(int add) {
        this.cash += add;
        Transaction transaction = new Transaction(add, "Add");
        transactions.add(transaction);
    }
}
