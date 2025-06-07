package ir.ac.kntu;

public class Address {
    private String title;
    private String province;
    private String city;
    private String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public Address(String title, String province, String city, String description) {
        setTitle(title);
        setProvince(province);
        setCity(city);
        setDescription(description);
    }

    public Address() {
    }

    

    @Override
    public String toString() {
        return ("Title:" + getTitle() + " - Province:" + getProvince() + " - City:" + getCity() + " - Description:"
                + getDescription());
    }
}
