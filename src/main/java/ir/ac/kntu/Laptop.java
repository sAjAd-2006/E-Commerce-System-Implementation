package ir.ac.kntu;

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
        return (super.toString() + "\nAdditional information Laptop ->" + " Graphics Processor:"
                + getGraphicsProcessor() + " Have Bluetooth:" + isBluetooth() + " Have Webcam:" + isWebcam());
    }
}