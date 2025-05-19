package ir.ac.kntu;

public class Address {
    private String Title;
    private String Province;
    private String City;
    private String Description;

    public void setTitle(String title) {
        Title = title;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public String getProvince() {
        return Province;
    }

    public String getCity() {
        return City;
    }

    public String getDescription() {
        return Description;
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
