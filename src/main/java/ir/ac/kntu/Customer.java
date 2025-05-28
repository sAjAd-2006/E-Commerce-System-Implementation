package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        shoppingCart = new ShoppingCart();
        wallet = new Wallet();
        addresses = new ArrayList<>();
        orders = new ArrayList<>();
        reportages = new ArrayList<>();
    }

    public Customer(String firstname, String lastname, String email, String phonenumber, String password) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPhonenumber(phonenumber);
        setPassword(password);
        wallet = new Wallet();
        addresses = new ArrayList<>();
        orders = new ArrayList<>();
        reportages = new ArrayList<>();
        shoppingCart = new ShoppingCart();
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
        Paginator<Address> paginator = new Paginator<>(addresses, 10);
        while (true) {
            if (this.shoppingCart.findTotal() == 0) {
                System.out.println("Your shopping cart is empty.");
                return;
            }
            if (!addresses.isEmpty()) {
                int select = paginator.paginate(0);
                if (select == -1) {
                    return;
                }
                Address address = addresses.get(select);
                if (address == null) {
                    System.out.println("You did not select an address.");
                    continue;
                }
                // boolean discount = true;
                // for (Kala kala : this.shoppingCart.getKalas()) {
                // kala.toString();
                // if (kala.getInventory() >= 1) {
                // if (!address.getCity().equalsIgnoreCase(kala.getSelerCity())) {
                // discount = false;
                // }
                // } else {
                // System.out.print(" ^ This item is out of stock and");
                // System.out.println(" will be automatically removed from your shopping
                // list.");
                // this.shoppingCart.getKalas().remove(kala);
                // }
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
        System.out.println("1) Complete Purchase\n2) Back\n3) Exit");
        String choice = scanner.nextLine();
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
        List<Boolean> vot = new ArrayList<>();
        for (Kala kala : kalass) {
            sellersNames.add(kala.getSelerName());
            kala.setInventory(kala.getInventory() - 1);
            vot.add(false);
        }
        Order newOrder = new Order(kalass, localDateTime, sellersNames, address, findTotal, shippingCost,
                (getFirstname() + " " + getLastname()), getEmail());
        newOrder.setIsVoted(vot);
        orders.add(newOrder);
        for (Seller seller : Vendilo.getSellers()) {
            for (Kala kala : kalass) {
                if (seller.getAgencyCode().equals(kala.getAgencyCodeOfSelers())) {
                    crOrder2(kala, localDateTime, seller, newOrder);
                }
            }
        }
    }

    public void crOrder2(Kala kala, LocalDateTime localDateTime, Seller seller, Order orderCustomer) {
        Address address = orderCustomer.getAddress();
        int shippingCost = orderCustomer.getShippingCost();
        List<Kala> kalas = new ArrayList<>();
        kalas.add(kala);
        Transaction transaction = new Transaction(localDateTime, (kala.getPrice() * 9 / 10), "Sale");
        seller.getWallet().getTransactions().add(transaction);
        seller.getWallet().setCash(seller.getWallet().getCash() + (kala.getPrice() * 9 / 10));
        Order order = new Order(kalas, localDateTime, null, address, (kala.getPrice() * 9 / 10),
                shippingCost, (getFirstname() + " " + getLastname()), getEmail());
        seller.getOrders().add(order);
    }

    public void searchKala(Scanner scanner) {
        while (true) {
            System.out.println("- - - - Search Kala - - - -\n");
            if (runBack(scanner) == 1) {
                break;
            }
            System.out.print("Enter at least one of the following fields\nProduct Name: ");
            String productName = scanner.nextLine();
            String productType = searchForProductType(scanner);
            List<String> sList = Arrays.asList(productName, productType);
            int min = 0, max = 0;
            while (true) {
                System.out.println("\nEnter price range(Default:ALL)");
                min = priceRange("min", scanner, 0);
                max = priceRange("max", scanner, min);
                if (max >= min) {
                    break;
                }
            }
            List<Kala> searchingKala = new ArrayList<>();
            searchingKala = searchingKala2(searchingKala, sList, min, max);
            showList(searchingKala, productType, scanner);
        }
    }

    public List<Kala> searchingKala2(List<Kala> searchingKala, List<String> sList, int min, int max) {
        String productName = sList.get(0), productType = sList.get(1);
        for (Kala kala : Seller.getKalas()) {
            if (kala.getPrice() <= max && kala.getPrice() >= min) {
                if (kala.getName().contains(productName) || "".equals(productName)) {
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
        return searchingKala;
    }

    public void showList(List<Kala> searchingKala, String productType, Scanner scanner) {
        while (true) {
            System.out.println("\nHow would you like the products to be displayed?\n");
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
            Paginator<Kala> paginator = new Paginator<>(searchingKala, 10);
            while (true) {
                int kal = paginator.paginate(0);
                if (kal == -1) {
                    break;
                }
                displayInformationAboutTheSelectedProduct(searchingKala.get(kal), productType, scanner);
            }
        }
    }

    // public void productSelection(List<Kala> searchingKala, String productType,
    // int iiii, Scanner scanner) {
    // while (true) {
    // // Scanner scanner = new Scanner(System.in);
    // System.out.println("Select the product you want: ");
    // System.out.println("Press 1 to go back, 0 to exit.");
    // String choice = scanner.nextLine();
    // switch (choice) {
    // case "1":
    // // scanner.close();
    // return;
    // case "0":
    // // scanner.close();
    // // System.exit(0);
    // ExitVendilo.exit(scanner);
    // default:
    // if (isInteger(choice)) {
    // int ran = Integer.parseInt(choice) - 1;
    // if (ran >= (iiii - 10) && ran < iiii) {
    // displayInformationAboutTheSelectedProduct(searchingKala.get(ran),
    // productType, scanner);
    // } else {
    // System.out.println("Out of range input.");
    // }
    // } else {
    // System.out.println("Incorrect input. Enter a number.");
    // }
    // break;
    // }
    // }
    // }

    public void displayInformationAboutTheSelectedProduct(Kala kala, String productType, Scanner scanner) {
        System.out.println(kala.toString() + " Inventory: " + kala.getInventory());
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
        System.out.println("\nProduct Type:\n1)Book\n2)Digital Goods\n3)ALL(Default)");
        String choice = scanner.nextLine();
        // scanner.close();
        switch (choice) {
            case "1":
                return "Book";
            case "2":
                System.out.println("\nDigital Goods Type:\n1)Mobile\n2)Laptop\n3)ALL Digital Goods(Default)");
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

    public int priceRange(String val, Scanner scanner, int min) {
        while (true) {
            System.out.print(val + ": ");
            String range = scanner.nextLine();
            if ("".equals(range)) {
                if ("max".equalsIgnoreCase(val)) {
                    return Integer.MAX_VALUE;
                } else {
                    if ("min".equalsIgnoreCase(val)) {
                        return 0;
                    }
                }
            } else {
                if (!isInteger(range)) {
                    if (Integer.parseInt(range) < min) {
                        System.out.println("The maximum must not be less than the minimum.");
                        continue;
                    }
                    return Integer.parseInt(range);
                } else {
                    System.out.println("invild input. enter a number");
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
