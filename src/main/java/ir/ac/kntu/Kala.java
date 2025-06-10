package ir.ac.kntu;

import java.util.HashMap;
import java.util.Map;

// import java.util.Objects;

abstract class Kala {
    private String name;
    private int Inventory;
    private int Price;
    private Type type;
    private Model model;
    private double voteNum;
    private double vote;
    private double averageScore = 0;
    private String agencyCoSeler;
    private String selerCity;
    private String selerName;
    private Map<String, Coment> votMap;

    public Map<String, Coment> getVotMap() {
        return votMap;
    }

    public void setSelerInfo(String name, String agencyCode, String city) {
        setSelerName(name);
        setAgencyCodeOfSelers(agencyCode);
        setSelerCity(city);
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

    public void calculatingTheAverage(int vote) {
        if (vote == 0) {
            return;
        }
        this.vote += vote;
        this.voteNum += 1;
        averageScore();
    }

    public double getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(double voteNum) {
        this.voteNum = voteNum;
    }

    public double getVote() {
        return vote;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }

    public String getAverageScore() {
        averageScore();
        if (averageScore == 0) {
            return "";
        }
        return Double.toString(averageScore);
    }

    public double getAverageScore2() {
        return this.averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public void setAgencyCodeOfSelers(String agencyCoOfSeler) {
        this.agencyCoSeler = agencyCoOfSeler;
    }

    public String getAgencyCodeOfSelers() {
        return agencyCoSeler;
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

    public String getSelerCity() {
        return selerCity;
    }

    public void setSelerCity(String selerCity) {
        this.selerCity = selerCity;
    }

    public Kala() {
        votMap = new HashMap<>();
    }



    @Override
    public String toString() {
        return ("Product name: " + getName() + " Product type: " + getModel() + " Seler name: "
                + getSelerName() + " Price: " + getPrice() + " Average score: " + getAverageScore()
                + " Seller's province: " + getSelerCity());
    }

    public String vendiloPlusSee() {
        return ("Product name: " + getName() + " Product type: " + getModel() + " Seler name: "
                + getSelerName() + " Price: " + getPrice() * 95 / 100 + " Average score: " + getAverageScore()
                + " Seller's province: " + getSelerCity());
    }

}