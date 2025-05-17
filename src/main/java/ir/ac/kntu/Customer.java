package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer extends Person {
    private List<Kala> shoppingCart;
    private Wallet wallet;
    private List<Address> addresses;

    public Customer() {
        shoppingCart = new ArrayList<>();
        wallet = new Wallet();
        addresses = new ArrayList<>();
    }

    public Customer(String firstname, String lastname, String email, String phonenumber, String password) {
        shoppingCart = new ArrayList<>();
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPhonenumber(phonenumber);
        setPassword(password);
        wallet = new Wallet();
        addresses = new ArrayList<>();
    }

    public List<Kala> getShoppingCarts() {
        return shoppingCart;
    }

    public void setShoppingCarts(List<Kala> shoppingCarts) {
        this.shoppingCart = shoppingCart;
    }

    public void addToShoppingCart(Kala kala) {
        this.shoppingCart.add(kala);
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addToWallet(int plus) {
        wallet.setCash(wallet.getCash() + plus);
    }

    public void searchKala() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("- - - - Search Kala - - - -\nEnter at least one of the following fields\nProduct Name: ");
        String productName = scanner.nextLine();
        String productType = searchForProductType();
        System.out.println("Enter price range");
        int min = priceRange("min");
        int max = priceRange("max");
        List<Kala> searchingKala = new ArrayList<>();
        for (Kala kala : Seller.getKalas()) {
            if (kala.getPrice() <= max && kala.getPrice() >= min) {
                if (kala.getName().contains(productName)) {
                    if (!productType.equalsIgnoreCase("all")) {
                        if (kala instanceof Book && productType.equalsIgnoreCase("book")) {
                            searchingKala.add(kala);
                        } else {
                            if (kala instanceof DigitalGoods && productType.equalsIgnoreCase("Digital Goods")) {
                                searchingKala.add(kala);
                            } else {
                                if (kala instanceof Mobile && productType.equalsIgnoreCase("mobile")) {
                                    searchingKala.add(kala);
                                } else {
                                    if (kala instanceof Laptop && productName.equalsIgnoreCase("laptop")) {
                                        searchingKala.add(kala);
                                    }
                                }
                            }
                        }
                    } else {
                        searchingKala.add(kala);
                    }
                }
            }
        }
        showList(searchingKala, productType);
        scanner.close();
    }

    public void showList(List<Kala> searchingKala, String productType) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "How would you like the products to be displayed?\n1)From the highest price\n2)From the lowest price\n3)Normal\nPress 0 to go back.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    scanner.close();
                    return;
                case "1":
                    upSortList(searchingKala);
                    break;
                case "2":
                    downSortList(searchingKala);
                    break;
                case "3":
                    break;
                default:
                    break;
            }
            for (int i = 0; i < searchingKala.size(); i++) {
                System.out.println(
                        (i + 1) + ")" + searchingKala.get(i).getName() + productType + searchingKala.get(i).getPrice());
            }
            productSelection(searchingKala, productType);
        }
    }

    public void productSelection(List<Kala> searchingKala, String productType) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select the product you want: ");
            System.out.println("Press 0 to go back.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    scanner.close();
                    return;
                default:
                    if (isInteger(choice)) {
                        int ran = Integer.parseInt(choice) - 1;
                        if (ran >= 0 && ran < searchingKala.size()) {
                            displayInformationAboutTheSelectedProduct(searchingKala.get(ran), productType);
                        } else {
                            System.out.println("Incorrect input. Enter a number.");
                        }
                    }
                    break;
            }
        }
    }

    private void displayInformationAboutTheSelectedProduct(Kala kala, String productType) {
        Scanner scanner = new Scanner(System.in);
        List<Seller> sellers = Vendilo.getSellers();
        Seller a = new Seller();
        for (Seller seller : sellers) {
            for (Kala kala2 : seller.getSellerKala()) {
                if (kala2.equals(kala)) {
                    a = seller;
                }
            }
        }
        System.out.println(kala.getName() + productType + a.getFirstname() + a.getLastname() + kala.getAverageScore()
                + a.getProvinceOfSale());
        addToCart(a, kala);
    }

    private void addToCart(Seller a, Kala kala) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to add this item to your cart?\n1)YES\n2)NO");
        String choice = scanner.nextLine();
        scanner.close();
        switch (choice) {
            case "1":
                addToShoppingCart(kala);
                return;
            default:
                return;
        }
    }

    public void upSortList(List<Kala> searchingKala) {
        searchingKala = searchingKala.stream()
                .sorted(Comparator.comparingInt(Kala::getPrice))
                .collect(Collectors.toList());
    }

    public void downSortList(List<Kala> searchingKala) {
        searchingKala = searchingKala.stream()
                .sorted(Comparator.comparingInt(Kala::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public String searchForProductType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Product Type:\n1)Book\n2)Digital Goods\n3)ALL");
        String choice = scanner.nextLine();
        scanner.close();
        switch (choice) {
            case "1":
                return "Book";
            case "2":
                System.out.println("Digital Goods Type:\n1)Mobile\n2)Laptop\n3)ALL");
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        return "Mobile";
                    case "2":
                        return "Laptop";
                    default:
                        return "Digital Goods";
                }
            default:
                return "ALL";
        }
    }

    public int priceRange(String val) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(val + ": ");
            String range = scanner.nextLine();
            if (range == null) {
                if (val.equalsIgnoreCase("max")) {
                    int ran = Integer.MAX_VALUE;
                    scanner.close();
                    return ran;
                } else {
                    if (val.equalsIgnoreCase("min")) {
                        int ran = Integer.MIN_VALUE;
                        scanner.close();
                        return ran;
                    }
                }
            } else {
                if (isInteger(range)) {
                    int ran = Integer.parseInt(range);
                    scanner.close();
                    return ran;
                } else {
                    System.out.println("Incorrect input. Enter a number.");
                }
            }
        }
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
