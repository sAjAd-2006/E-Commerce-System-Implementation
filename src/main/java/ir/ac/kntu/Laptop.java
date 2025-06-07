package ir.ac.kntu;

// import java.util.Objects;

public class Laptop extends DigitalGoods {
    private String graphicsProcessor;
    private boolean bluetooth;
    private boolean webcam;

    public String getGraphicsProcessor() {
        return graphicsProcessor;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public boolean isWebcam() {
        return webcam;
    }

    public void setGraphicsProcessor(String graphicsProcessor) {
        this.graphicsProcessor = graphicsProcessor;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public void setWebcam(boolean webcam) {
        this.webcam = webcam;
    }

    public Laptop() {
        this.setType(Type.DIGITALGOODS);
        this.setModel(Model.LAPTOP);
    }

    @Override
    public String toString() {
        return (super.toString() + "\n      Additional information Laptop ->" + " Graphics Processor:"
                + getGraphicsProcessor() + " Have Bluetooth:" + isBluetooth() + " Have Webcam:" + isWebcam());
    }

    @Override
    public String vendiloPlusSee() {
        return (super.vendiloPlusSee() + "\n      Additional information Laptop ->" + " Graphics Processor:"
                + getGraphicsProcessor() + " Have Bluetooth:" + isBluetooth() + " Have Webcam:" + isWebcam());
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
    // Laptop laptop = (Laptop) obj;
    // return bluetooth == laptop.bluetooth &&
    // webcam == laptop.webcam &&
    // Objects.equals(graphicsProcessor, laptop.graphicsProcessor);
    // }

    // @Override
    // public int hashCode() {
    // return Objects.hash(super.hashCode(), graphicsProcessor, bluetooth, webcam);
    // }
}