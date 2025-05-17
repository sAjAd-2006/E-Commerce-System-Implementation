package ir.ac.kntu;

import java.util.List;

abstract class Kala {
    private String name;
    private int Inventory;
    private int Price;
    private Type type;
    private int voteNum;
    private int vote;
    private int averageScore = 0;
    private List<String> agencyCodeOfSelers;

    public void averageScore() {
        this.averageScore = vote / voteNum;
    }

    public int getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public void setAgencyCodeOfSelers(List<String> sellers) {
        this.agencyCodeOfSelers = agencyCodeOfSelers;
    }

    public List<String> getAgencyCodeOfSelers() {
        return agencyCodeOfSelers;
    }

    public String getName() {
        return name;
    }

    public int getInventory() {
        return Inventory;
    }

    public int getPrice() {
        return Price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInventory(int inventory) {
        Inventory = inventory;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}