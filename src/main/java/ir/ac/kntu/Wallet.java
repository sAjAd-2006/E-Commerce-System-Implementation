package ir.ac.kntu;

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
    }

    public Wallet(int cash) {
        this.cash = cash;
    }

    public boolean WithdrawFromWallet(int draw) {
        if (this.cash - draw >= 0) {
            this.cash -= draw;
            Transaction a = new Transaction(draw, "Shopping");
            transactions.add(a);
            return true;
        } else {
            System.out.println("The amount requested is greater than your account balance.");
            return false;
        }
    }
}
