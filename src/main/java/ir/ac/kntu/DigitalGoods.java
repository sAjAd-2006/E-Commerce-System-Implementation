package ir.ac.kntu;

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
                + " Internal memory size:" + getInternalMemorySize() + "G Amount of RAM:" + getAmountOfRAM() + "G");
    }
}