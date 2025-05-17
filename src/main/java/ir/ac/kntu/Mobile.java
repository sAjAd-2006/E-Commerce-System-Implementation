package ir.ac.kntu;

public class Mobile extends DigitalGoods {
    private String rearCameraResolution;
    private String frontCameraResolution;
    private String internetNetwork;

    public String getRearCameraResolution() {
        return rearCameraResolution;
    }

    public String getFrontCameraResolution() {
        return frontCameraResolution;
    }

    public String getInternetNetwork() {
        return internetNetwork;
    }

    public void setRearCameraResolution(String rearCameraResolution) {
        this.rearCameraResolution = rearCameraResolution;
    }

    public void setFrontCameraResolution(String frontCameraResolution) {
        this.frontCameraResolution = frontCameraResolution;
    }

    public void setInternetNetwork(String internetNetwork) {
        this.internetNetwork = internetNetwork;
    }

    public Mobile() {
        this.setType(Type.DIGITALGOODS);
        this.setModel(Model.MOBILE);
    }
    
}
