package ir.ac.kntu;

public class Coment {
    private String name;
    private String coment;
    private int rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Coment(String name, String coment, int rate) {
        this.name = name;
        this.coment = coment;
        this.rate = rate;
    }

    public Coment() {
    }

    @Override
    public String toString() {
        return "Coment [CustomerN1ame=" + name + ", coment=" + coment + ", rate=" + rate + "]";
    }

}
