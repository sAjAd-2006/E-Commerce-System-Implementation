package ir.ac.kntu;

public class Book extends Kala {
    private String authorsName;
    private String numberOfPages;
    private Age ageCategory;
    private String idISBN;

    public String getAuthorsName() {
        return authorsName;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public Age getAgeCategory() {
        return ageCategory;
    }

    public String getIdISBN() {
        return idISBN;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setAgeCategory(Age ageCategory) {
        this.ageCategory = ageCategory;
    }

    public void setIdISBN(String idISBN) {
        this.idISBN = idISBN;
    }

    public Book() {
        this.setType(Type.BOOK);
        this.setModel(Model.BOOK);
    }

    @Override
    public String toString() {
        return (super.toString() + "\nAdditional information Book ->" + " Authors name:" + getAuthorsName()
                + " Number of pages:" + getNumberOfPages() + " Age category:" + getAgeCategory() + " Id ISBN:"
                + getIdISBN());
    }
}
