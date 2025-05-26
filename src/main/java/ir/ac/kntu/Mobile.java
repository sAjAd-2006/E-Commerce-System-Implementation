package ir.ac.kntu;

public class Mobile extends DigitalGoods {
    private String rearCamReso;
    private String frontCamReso;
    private String internetNetwork;

    public String getRearCameraResolution() {
        return rearCamReso;
    }

    public String getFrontCameraResolution() {
        return frontCamReso;
    }

    public String getInternetNetwork() {
        return internetNetwork;
    }

    public void setRearCameraResolution(String rearCamReso) {
        this.rearCamReso = rearCamReso;
    }

    public void setFrontCameraResolution(String frontCamReso) {
        this.frontCamReso = frontCamReso;
    }

    public void setInternetNetwork(String internetNetwork) {
        this.internetNetwork = internetNetwork;
    }

    public Mobile() {
        this.setType(Type.DIGITALGOODS);
        this.setModel(Model.MOBILE);
    }

    @Override
    public String toString() {
        return (super.toString() + "\n      Additional information Mobile ->" + " Rear Camera Resolution:"
                + getRearCameraResolution() + " Front Camera Resolution:" + getFrontCameraResolution()
                + " Internet Network:" + getInternetNetwork());
    }
}
