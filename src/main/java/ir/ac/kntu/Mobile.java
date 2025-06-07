package ir.ac.kntu;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Mobile mobile = (Mobile) obj;
        return Objects.equals(rearCamReso, mobile.rearCamReso) &&
                Objects.equals(frontCamReso, mobile.frontCamReso) &&
                Objects.equals(internetNetwork, mobile.internetNetwork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rearCamReso, frontCamReso, internetNetwork);
    }
}
