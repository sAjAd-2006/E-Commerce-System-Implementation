package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer extends Person {
    private ShoppingCart shoppingCart = new ShoppingCart();
    private Wallet wallet = new Wallet();
    private List<Order> orders = new ArrayList<>();
    private List<Seller> sellers = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private List<Reportage> reportages = new ArrayList<>();
    private List<DiscountCode> discountCodes = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();
    private boolean vendiloPlus = false;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public boolean isVendiloPlus() {
        return vendiloPlus;
    }

    public void setVendiloPlus(boolean vendiloPlus) {
        this.vendiloPlus = vendiloPlus;
    }

    public List<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

    public void setDiscountCodes(List<DiscountCode> discountCodes) {
        this.discountCodes = discountCodes;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

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
    }

    public Customer(String firstname, String lastname, String email, String phonenumber, String password) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPhonenumber(phonenumber);
        setPassword(password);
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

    public int shoppingMuch() {
        LocalDateTime loDTime = LocalDateTime.now().minusMonths(1);
        int price = 0;
        for (Transaction transaction : this.wallet.getTransactions()) {
            if (transaction.getLocalDateTime().isAfter(loDTime) && "Shopping".equals(transaction.getWhy())) {
                price += transaction.getPrice();
            }
        }
        return price;
    }

    public void continueShopping(Scanner scanner) {
        int shippingCost = 30;
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
                int dis = discount(address);
                if (dis == 1 && vendiloPlus) {
                    shippingCost = 0;
                }
                if (dis == 1 || vendiloPlus) {
                    shippingCost /= 3;
                }
                seeTotalAndContinue(scanner, address, shippingCost);
            } else {
                System.out.println("Please register your address first.");
                return;
            }
        }
    }

    private void seeTotalAndContinue(Scanner scanner, Address address, int shippingCost) {
        if (vendiloPlus) {
            System.out.println("Price: " + shoppingCart.findTotal() * 95 / 100 + "\nShipping Cost: " + shippingCost);
            continueShoppingNow(shippingCost, address, scanner,
                    useDiscountCode(scanner, shoppingCart.findTotal() * 95 / 100));
        } else {
            System.out.println("Price: " + shoppingCart.findTotal() + "\nShipping Cost: " + shippingCost);
            int total = useDiscountCode(scanner, shoppingCart.findTotal());
            System.out.println("Price: " + total + "\nShipping Cost: " + shippingCost);
            continueShoppingNow(shippingCost, address, scanner, total);
        }
    }

    private int useDiscountCode(Scanner scanner, int totalPrice) {
        boolean numUse = false;
        for (DiscountCode discountCode : discountCodes) {
            if (discountCode.getNumbCanBeUsed() > 0) {
                numUse = true;
            }
        }
        if (!numUse) {
            return totalPrice;
        }
        while (true) {
            Color.printWhiteBold("Do you want to apply a discount code? y/n(Default)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    Paginator<DiscountCode> paginator = new Paginator<>(discountCodes, 10);
                    int select = paginator.paginate(0);
                    if (select == -1) {
                        return totalPrice;
                    }
                    return discountCodes.get(select).discountCalculation(totalPrice);
                default:
                    return totalPrice;
            }
        }
    }

    public int discount(Address address) {
        boolean discount = true;
        for (Kala kala : this.shoppingCart.getKalasMap().keySet()) {
            kala.toString();
            if (kala.getInventory() >= this.shoppingCart.getKalasMap().get(kala)) {
                if (!address.getCity().equalsIgnoreCase(kala.getSelerCity())) {
                    discount = false;
                }
            } else {
                if (kala.getInventory() >= 1) {
                    Color.printYellow("Only " + kala.getInventory()
                            + " of the desired item remain. The rest will be deducted from your cart.");
                    this.shoppingCart.getKalasMap().replace(kala, kala.getInventory());
                } else {
                    System.out.print(" ^ This item is out of stock and");
                    System.out.println(" will be automatically removed from your shopping list.");
                    this.shoppingCart.getKalasMap().remove(kala);
                }
            }
        }
        if (discount) {
            return 1;
        }
        return -1;
    }

    public void continueShoppingNow(int shippingCost, Address address, Scanner scanner, int totalPrice) {
        System.out.println("1) Complete Purchase\n2) Back\n3) Exit");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                if (this.wallet.withdrawFromWallet((totalPrice + shippingCost), "Shopping")) {
                    crOrder(address, shippingCost, totalPrice);
                    LinkedHashMap<Kala, Integer> newMap = new LinkedHashMap<>();
                    shoppingCart.setKalasMap(newMap);
                    shoppingCart.setTotalPrice(0);
                }
                break;

            default:
                break;
        }
    }

    public void crOrder(Address address, int shippingCost, int totalPrice) {
        LocalDateTime localDateTime = this.wallet.getTransactions().getLast().getLocalDateTime();
        Map<Kala, Integer> kalaForOrder = this.shoppingCart.getKalasMap();
        Map<Kala, Boolean> kalaVoteForOrder = new LinkedHashMap<>();
        List<String> sellersNames = new ArrayList<>();
        for (Kala kala : kalaForOrder.keySet()) {
            sellersNames.add(kala.getSelerName());
            kala.setInventory(kala.getInventory() - kalaForOrder.get(kala));
            kalaVoteForOrder.put(kala, false);
        }
        Order newOrder = new Order(kalaForOrder, localDateTime, sellersNames, address, totalPrice, shippingCost,
                (getFirstname() + " " + getLastname()), getEmail());
        newOrder.setKalasVoteMap(kalaVoteForOrder);
        orders.add(newOrder);
        for (Seller seller : this.sellers) {
            for (Kala kala : kalaForOrder.keySet()) {
                if (seller.getAgencyCode().equals(kala.getAgencyCodeOfSelers())) {
                    crOrder2(kala, localDateTime, seller, newOrder);
                }
            }
        }
    }

    public void crOrder2(Kala kala, LocalDateTime localDateTime, Seller seller, Order orderCustomer) {
        Address address = orderCustomer.getAddress();
        int shippingCost = orderCustomer.getShippingCost();
        Map<Kala, Integer> kalasForOrder = new LinkedHashMap<>();
        int kalaNumber = this.shoppingCart.getKalasMap().get(kala);
        kalasForOrder.put(kala, kalaNumber);
        Transaction transaction = new Transaction(localDateTime, (kala.getPrice() * kalaNumber * 9 / 10), "Sale");
        seller.getWallet().getTransactions().add(transaction);
        seller.getWallet().setCash(seller.getWallet().getCash() + (kala.getPrice() * kalaNumber * 9 / 10));
        Order order = new Order(kalasForOrder, localDateTime, null, address, (kala.getPrice() * kalaNumber * 9 / 10),
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
                case "1" -> searchingKala = upSortList(searchingKala);
                case "2" -> searchingKala = downSortList(searchingKala);
                default -> System.out.print("");
            }
            if (runBack(scanner) == 1) {
                break;
            }
            display(searchingKala, productType, scanner);
        }
    }

    public void display(List<Kala> searchingKala, String productType, Scanner scanner) {
        if (vendiloPlus) {
            VendiloPlusSeeKala vPlusSeeKala = new VendiloPlusSeeKala(searchingKala, 10);
            while (true) {
                int kal = vPlusSeeKala.paginate(0);
                if (kal == -1) {
                    break;
                }
                displayInformationAboutTheSelectedProduct(searchingKala.get(kal), productType, scanner);
            }
        } else {
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

    public void displayInformationAboutTheSelectedProduct(Kala kala, String productType, Scanner scanner) {
        if (vendiloPlus) {
            System.out.println(kala.vendiloPlusSee() + " Inventory: " + kala.getInventory());
        } else {
            System.out.println(kala.toString() + " Inventory: " + kala.getInventory());
        }
        addToCart(kala, scanner);
    }

    public void addToCart(Kala kala, Scanner scanner) {
        if (runBack(scanner) == 1) {
            return;
        }
        if (kala.getInventory() == 0) {
            System.out.println("This item is out of stock." +
                    "\nWould you like to be notified when it becomes available? y/n(Default)");
            crNotifKala(scanner, kala);
            return;
        }
        System.out.println("Do you want to add this item to your cart?\n1>YES\n2>NO(Default)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                if (this.shoppingCart.getKalasMap().containsKey(kala)) {
                    int oldNum = this.shoppingCart.getKalasMap().get(kala);
                    this.shoppingCart.getKalasMap().replace(kala, oldNum, oldNum + 1);
                } else {
                    this.shoppingCart.getKalasMap().put(kala, 1);
                }
                return;
            default:
                return;
        }
    }

    private void crNotifKala(Scanner scanner, Kala kala) {
        String choice = scanner.nextLine();
        if ("y".equalsIgnoreCase(choice)) {
            for (Notification notification : notifications) {
                if (notification instanceof KalaNotification) {
                    KalaNotification kalaNotif = (KalaNotification) notification;
                    if (kalaNotif.getKala().equals(kala)) {
                        notifications.remove(notification);
                        break;
                    }
                }
            }
            KalaNotification kalaNotif = new KalaNotification(kala);
            notifications.add(kalaNotif);
        }
    }

    public List<Kala> upSortList(List<Kala> searchingKala) {
        return searchingKala.stream()
                .sorted(Comparator.comparingInt(Kala::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public List<Kala> downSortList(List<Kala> searchingKala) {
        return searchingKala.stream()
                .sorted(Comparator.comparingInt(Kala::getPrice))
                .collect(Collectors.toList());
    }

    public String searchForProductType(Scanner scanner) {
        System.out.println("\nProduct Type:\n1)Book\n2)Digital Goods\n3)ALL(Default)");
        String choice = scanner.nextLine();
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
                if (isInteger(range)) {
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
        return (super.toString() + " Email: " + getEmail());
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