package ir.ac.kntu;

// import java.util.Objects;

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
        return (super.toString() + "\n      Additional information Book ->" + " Authors name:" + getAuthorsName()
                + " Number of pages:" + getNumberOfPages() + " Age category:" + getAgeCategory() + " Id ISBN:"
                + getIdISBN());
    }

    @Override
    public String vendiloPlusSee() {
        return (super.vendiloPlusSee() + "\n      Additional information Book ->" + " Authors name:" + getAuthorsName()
                + " Number of pages:" + getNumberOfPages() + " Age category:" + getAgeCategory() + " Id ISBN:"
                + getIdISBN());
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj) {
    //         return true;
    //     }
    //     if (obj == null || getClass() != obj.getClass()) {
    //         return false;
    //     }
    //     if (!super.equals(obj)) {
    //         return false;
    //     }
    //     Book book = (Book) obj;
    //     return Objects.equals(authorsName, book.authorsName) &&
    //             Objects.equals(numberOfPages, book.numberOfPages) &&
    //             Objects.equals(ageCategory, book.ageCategory) &&
    //             Objects.equals(idISBN, book.idISBN);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(super.hashCode(), authorsName, numberOfPages, ageCategory, idISBN);
    // }
}
