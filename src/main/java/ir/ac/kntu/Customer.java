package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer extends Person {
    private ShoppingCart shoppingCart;
    private Wallet wallet;
    private List<Address> addresses;
    private List<Order> orders;
    private List<Reportage> reportages;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Reportage> getReportages() {
        return reportages;
    }

    public void setReportages(List<Reportage> reportages) {
        this.reportages = reportages;
    }

    public Customer() {
        wallet = new Wallet();
        addresses = new ArrayList<>();
    }

    public Customer(String firstname, String lastname, String email, String phonenumber, String password) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPhonenumber(phonenumber);
        setPassword(password);
        wallet = new Wallet();
        addresses = new ArrayList<>();
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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void continueShopping(Scanner scanner) {
        int shippingCost = 10;
        while (true) {
            if (this.shoppingCart.findTotal() == 0) {
                System.out.println("Your shopping cart is empty.");
                return;
            }
            if (!addresses.isEmpty()) {
                Paginator<Address> paginator = new Paginator<>(addresses, 10);
                Address address = addresses.get(paginator.paginate(0));
                if (address == null) {
                    System.out.println("You did not select an address.");
                    continue;
                }
                // boolean discount = true;
                // for (Kala kala : this.shoppingCart.getKalas()) {
                //     kala.toString();
                //     if (kala.getInventory() >= 1) {
                //         if (!address.getCity().equalsIgnoreCase(kala.getSelerCity())) {
                //             discount = false;
                //         }
                //     } else {
                //         System.out.print(" ^ This item is out of stock and");
                //         System.out.println(" will be automatically removed from your shopping list.");
                //         this.shoppingCart.getKalas().remove(kala);
                //     }
                // }
                if (discount(address) == 1) {
                    shippingCost = shippingCost * 7 / 10;
                }
                System.out.println("Price: " + shoppingCart.findTotal() + "\nShipping Cost: " + shippingCost);
                continueShoppingNow(shippingCost, address, scanner);
            } else {
                System.out.println("Please register your address first.");
                return;
            }
        }
    }

    public int discount(Address address) {
        boolean discount = true;
        for (Kala kala : this.shoppingCart.getKalas()) {
            kala.toString();
            if (kala.getInventory() >= 1) {
                if (!address.getCity().equalsIgnoreCase(kala.getSelerCity())) {
                    discount = false;
                }
            } else {
                System.out.print(" ^ This item is out of stock and");
                System.out.println(" will be automatically removed from your shopping list.");
                this.shoppingCart.getKalas().remove(kala);
            }
        }
        if (discount) {
            return 1;
        }
        return -1;
    }

    public void continueShoppingNow(int shippingCost, Address address, Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        System.out.println("1) Complete Purchase\n2) Back\n3) Exit");
        String choice = scanner.nextLine();
        // scanner.close();
        switch (choice) {
            case "1":
                if (this.wallet.withdrawFromWallet((this.shoppingCart.findTotal() + shippingCost), "Shopping")) {
                    crOrder(address, this.shoppingCart.findTotal(), shippingCost);
                    List<Kala> kalas = new ArrayList<>();
                    shoppingCart.setKalas(kalas);
                    shoppingCart.setTotalPrice(0);
                }
                break;

            default:
                break;
        }
    }

    public void crOrder(Address address, int findTotal, int shippingCost) {
        LocalDateTime localDateTime = this.wallet.getTransactions().getLast().getLocalDateTime();
        List<Kala> kalass = this.shoppingCart.getKalas();
        List<String> sellersNames = new ArrayList<>();
        for (Kala kala : kalass) {
            sellersNames.add(kala.getSelerName());
            kala.setInventory(kala.getInventory() - 1);
        }
        Order newOrder = new Order(kalass, localDateTime, sellersNames, address, findTotal, shippingCost, "", "");
        for (int i = 0; i < kalass.size(); i++) {
            newOrder.getIsVoted().add(false);
        }
        orders.add(newOrder);
        for (Seller seller : Vendilo.getSellers()) {
            for (Kala kala : kalass) {
                if (seller.getAgencyCode().equals(kala.getAgencyCodeOfSelers())) {
                    List<Kala> kalas = new ArrayList<>();
                    kalas.add(kala);
                    Transaction transaction = new Transaction(localDateTime, (kala.getPrice() * 9 / 10), "Sale");
                    seller.getWallet().getTransactions().add(transaction);
                    seller.getWallet().setCash(seller.getWallet().getCash() + (kala.getPrice() * 9 / 10));
                    Order order = new Order(kalas, localDateTime, null, address, (kala.getPrice() * 9 / 10),
                            shippingCost,
                            (getFirstname() + " " + getLastname()), getEmail());
                    seller.getOrders().add(order);
                }
            }
        }
    }

    public void searchKala(Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("- - - - Search Kala - - - -\n");
            if (runBack(scanner) == 1) {
                break;
            }
            System.out.println("Enter at least one of the following fields\nProduct Name: ");
            String productName = scanner.nextLine();
            String productType = searchForProductType(scanner);
            int min = 0, max = 0;
            while (true) {
                System.out.println("Enter price range(Default:ALL)");
                min = priceRange("min", scanner);
                max = priceRange("max", scanner);
                if (max >= min) {
                    break;
                }
            }
            List<Kala> searchingKala = new ArrayList<>();
            for (Kala kala : Seller.getKalas()) {
                if (kala.getPrice() <= max && kala.getPrice() >= min) {
                    if (kala.getName().contains(productName)) {
                        if (!"all".equalsIgnoreCase(productType)) {
                            if (kala instanceof Book && "book".equalsIgnoreCase(productType)) {
                                searchingKala.add(kala);
                            } else {
                                if (kala instanceof DigitalGoods && "Digital Goods".equalsIgnoreCase(productType)) {
                                    searchingKala.add(kala);
                                } else {
                                    if (kala instanceof Mobile && "mobile".equalsIgnoreCase(productType)) {
                                        searchingKala.add(kala);
                                    } else {
                                        if (kala instanceof Laptop && "laptop".equalsIgnoreCase(productName)) {
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
            showList(searchingKala, productType, scanner);
        }
        // scanner.close();
    }

    public void showList(List<Kala> searchingKala, String productType, Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("How would you like the products to be displayed?\n");
            System.out.println("1)From the highest price\n2)From the lowest price\n3)Normal(Default)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> upSortList(searchingKala);
                case "2" -> downSortList(searchingKala);
                default -> System.out.print("");
            }
            if (runBack(scanner) == 1) {
                break;
            }
            for (int i = 0; i < searchingKala.size(); i++) {
                if (i % 10 == 0 && i != 0) {
                    if (runBack(scanner) == 1) {
                        // scanner.close();
                        return;
                    }
                    productSelection(searchingKala, productType, i, scanner);
                    System.out.println("-------------------------\n<- Previous page | Next page ->");
                    String choicePage = scanner.nextLine();
                    boolean runPag = true;
                    while (runPag) {
                        switch (choicePage) {
                            case "<-":
                                i -= 10;
                                runPag = false;
                                break;
                            case "->":
                                runPag = false;
                                break;
                            default:
                                System.out.println("The selected option is invalid.");
                                break;
                        }
                    }
                }
                System.out.println("Page <" + (i + 1) + ">");
                System.out.println((i + 1) + ") Product name:" + searchingKala.get(i).getName() + " Product type:"
                        + productType + " Seler name:" + searchingKala.get(i).getSelerName() + " Price:"
                        + searchingKala.get(i).getPrice() + " Average score:" + searchingKala.get(i).getAverageScore());
            }
        }
        // scanner.close();
    }

    public void productSelection(List<Kala> searchingKala, String productType, int iiii, Scanner scanner) {
        while (true) {
            // Scanner scanner = new Scanner(System.in);
            System.out.println("Select the product you want: ");
            System.out.println("Press 1 to go back, 0 to exit.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // scanner.close();
                    return;
                case "0":
                    // scanner.close();
                    // System.exit(0);
                    ExitVendilo.exit(scanner);
                default:
                    if (isInteger(choice)) {
                        int ran = Integer.parseInt(choice) - 1;
                        if (ran >= (iiii - 10) && ran < iiii) {
                            displayInformationAboutTheSelectedProduct(searchingKala.get(ran), productType, scanner);
                        } else {
                            System.out.println("Out of range input.");
                        }
                    } else {
                        System.out.println("Incorrect input. Enter a number.");
                    }
                    break;
            }
        }
    }

    public void displayInformationAboutTheSelectedProduct(Kala kala, String productType, Scanner scanner) {
        System.out.println(kala.toString());
        addToCart(kala, scanner);
    }

    public void addToCart(Kala kala, Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        if (runBack(scanner) == 1) {
            // scanner.close();
            return;
        }
        System.out.println("Do you want to add this item to your cart?\n1>YES\n2>NO(Default)");
        String choice = scanner.nextLine();
        // scanner.close();
        switch (choice) {
            case "1":
                this.shoppingCart.getKalas().add(kala);
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

    public String searchForProductType(Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        System.out.println("Product Type:\n1)Book\n2)Digital Goods\n3)ALL(Default)");
        String choice = scanner.nextLine();
        // scanner.close();
        switch (choice) {
            case "1":
                return "Book";
            case "2":
                System.out.println("Digital Goods Type:\n1)Mobile\n2)Laptop\n3)ALL Digital Goods(Default)");
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

    public int priceRange(String val, Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(val + ": ");
            String range = scanner.nextLine();
            if (range == null) {
                if ("max".equalsIgnoreCase(val)) {
                    // int ran = Integer.MAX_VALUE;
                    // scanner.close();
                    return Integer.MAX_VALUE;
                } else {
                    if ("min".equalsIgnoreCase(val)) {
                        // int ran = 0;
                        // scanner.close();
                        return 0;
                    }
                }
            } else {
                if (isInteger(range)) {
                    // int ran = Integer.parseInt(range);
                    // scanner.close();
                    return Integer.parseInt(range);
                } else {
                    System.out.println("Incorrect input. Enter a number.");
                }
            }
        }
    }

    @Override
    public String toString() {
        return ("Name: " + getFirstname() + " " + getLastname() + " Email: " + getEmail());
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
