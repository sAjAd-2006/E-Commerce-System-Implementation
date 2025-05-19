package ir.ac.kntu;

abstract class DigitalGoods extends Kala {
    private String Brand;
    private String internalMemorySize;
    private String amountOfRAM;

    public String getBrand() {
        return Brand;
    }

    public String getInternalMemorySize() {
        return internalMemorySize;
    }

    public String getAmountOfRAM() {
        return amountOfRAM;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setInternalMemorySize(String internalMemorySize) {
        this.internalMemorySize = internalMemorySize;
    }

    public void setAmountOfRAM(String amountOfRAM) {
        this.amountOfRAM = amountOfRAM;
    }

    public DigitalGoods() {
    }

    @Override
    public String toString() {
        return (super.toString() + "\nAdditional information Digital good ->" + " Brand:" + getBrand()
                + " Internal memory size:" + getInternalMemorySize() + " Amount of RAM:" + getAmountOfRAM());
    }
}