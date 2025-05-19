package ir.ac.kntu;

import java.util.List;

abstract class Kala {
    private String name;
    private int Inventory;
    private int Price;
    private Type type;
    private Model model;
    private int voteNum;
    private int vote;
    private boolean voted;
    private double averageScore = 0;
    private String agencyCodeOfSelers;
    private String selerCity;
    private String selerName;

    public void setSelerInfo(String name, String agencyCode, String city) {
        setSelerName(name);
        setAgencyCodeOfSelers(agencyCode);
        setSelerCity(city);
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public String getSelerName() {
        return selerName;
    }

    public void setSelerName(String selerName) {
        this.selerName = selerName;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void averageScore() {
        if (voteNum == 0) {
            return;
        }
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

    public double getAverageScore() {
        averageScore();
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public void setAgencyCodeOfSelers(String sellers) {
        this.agencyCodeOfSelers = agencyCodeOfSelers;
    }

    public String getAgencyCodeOfSelers() {
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

    public Kala() {
    }

    public String getSelerCity() {
        return selerCity;
    }

    public void setSelerCity(String selerCity) {
        this.selerCity = selerCity;
    }

    @Override
    public String toString() {
        return ("Product name:" + getName() + " Product type:" + getModel() + " Seler name:"
                + getSelerName() + " Price:" + getPrice() + " Average score:" + getAverageScore()
                + " Seller's province:" + getSelerCity());
    }
}