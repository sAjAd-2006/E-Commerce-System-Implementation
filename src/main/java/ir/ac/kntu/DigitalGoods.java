package ir.ac.kntu;

// import java.util.Objects;

abstract class DigitalGoods extends Kala {
    private String Brand;
    private String interMemoSize;
    private String amountOfRAM;

    public String getBrand() {
        return Brand;
    }

    public String getInternalMemorySize() {
        return interMemoSize;
    }

    public String getAmountOfRAM() {
        return amountOfRAM;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setInternalMemorySize(String interMemoSize) {
        this.interMemoSize = interMemoSize;
    }

    public void setAmountOfRAM(String amountOfRAM) {
        this.amountOfRAM = amountOfRAM;
    }

    public DigitalGoods() {
    }

    @Override
    public String toString() {
        return (super.toString() + "\n      Additional information Digital good ->" + " Brand:" + getBrand()
                + " Internal memory size:" + getInternalMemorySize() + " Amount of RAM:" + getAmountOfRAM());
    }

    @Override
    public String vendiloPlusSee() {
        return (super.vendiloPlusSee() + "\n      Additional information Digital good ->" + " Brand:" + getBrand()
                + " Internal memory size:" + getInternalMemorySize() + "G Amount of RAM:" + getAmountOfRAM() + "G");
    }
    // @Override
    // public boolean equals(Object obj) {
    // if (this == obj) {
    // return true;
    // }
    // if (obj == null || getClass() != obj.getClass()) {
    // return false;
    // }
    // if (!super.equals(obj)) {
    // return false;
    // }
    // DigitalGoods that = (DigitalGoods) obj;
    // return Objects.equals(Brand, that.Brand) &&
    // Objects.equals(interMemoSize, that.interMemoSize) &&
    // Objects.equals(amountOfRAM, that.amountOfRAM);
    // }

    // @Override
    // public int hashCode() {
    // return Objects.hash(super.hashCode(), Brand, interMemoSize, amountOfRAM);
    // }

}