package ir.ac.kntu;

public class Manager extends Person {
    private String agencyName;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Manager(String firstname, String lastname, String agencyName, String password, int accessLevel) {
        setFirstname(firstname);
        setLastname(lastname);
        this.agencyName = agencyName;
        setPassword(password);
        setAccessLevel(accessLevel);
    }


}
