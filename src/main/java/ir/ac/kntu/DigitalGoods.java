package ir.ac.kntu;

abstract class DigitalGoods extends Kala {
    private String Brand;
    private String internalMemorySize;
    private String amountOfRAM;
    // private Model model;

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

    // public Model getModel() {
    //     return model;
    // }

    // public void setModel(Model model) {
    //     this.model = model;
    // }
}