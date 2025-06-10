package ir.ac.kntu;

public class DiscountCode {
    private String code;
    private Kind kind;
    private int discountAmount;
    private int numbCanBeUsed = 0;
    private String vahed;

    public void setVahed(String vahed) {
        this.vahed = vahed;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getNumbCanBeUsed() {
        return numbCanBeUsed;
    }

    public void setNumbCanBeUsed(int numbCanBeUsed) {
        this.numbCanBeUsed = numbCanBeUsed;
    }

    public DiscountCode(String code, Kind kind, int discountAmount, int numbCanBeUsed) {
        this.code = code;
        this.kind = kind;
        this.discountAmount = discountAmount;
        this.numbCanBeUsed = numbCanBeUsed;
        vahed = "$";
        if (kind.equals(Kind.Percentage)) {
            vahed = "%";
        }
    }

    public int discountCalculation(int totalPrice) {
        if (numbCanBeUsed == 0) {
            return totalPrice;
        }
        if (kind == Kind.Percentage) {
            numbCanBeUsed--;
            return totalPrice * (100 - discountAmount) / 100;
        } else {
            if (totalPrice * 10 > discountAmount) {
                numbCanBeUsed--;
                return totalPrice - discountAmount;
            } else {
                Color.printYellow(
                        "\nThe purchase amount is too small to use the discount code and the discount code is not applied.");
                return totalPrice;
            }
        }
    }

    @Override
    public String toString() {
        return "DiscountCode [code=" + code + ", kind=" + kind + ", DiscountAmount=" + discountAmount
                + vahed + ", NumbCanBeUsed=" + numbCanBeUsed + "]";
    }

}
