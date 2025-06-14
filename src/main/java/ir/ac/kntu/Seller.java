package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Seller extends Person {
    private String codeMely;
    private String storeTitle;
    private String provinceOfSale;
    private String agencyCode;
    private static List<Kala> kalas = new ArrayList<>();
    private List<Kala> sellerKala;
    private Wallet wallet;
    private List<Order> orders;
    private String reasonForReject;
    private Reportage reportage = new Reportage("a");
    private List<Notification> notifications = new ArrayList<>();

    public Reportage getReportage() {
        return reportage;
    }

    public void setReportage(Reportage reportage) {
        this.reportage = reportage;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getReasonForRejection() {
        return reasonForReject;
    }

    public void setReasonForRejection(String reasonForReject) {
        this.reasonForReject = reasonForReject;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setSellerKala(List<Kala> kalas) {
        this.sellerKala = kalas;
    }

    public List<Kala> getSellerKala() {
        return sellerKala;
    }

    public static List<Kala> getKalas() {
        return kalas;
    }

    public void setStoreTitle(String storeTitle) {
        this.storeTitle = storeTitle;
    }

    public void setProvinceOfSale(String provinceOfSale) {
        this.provinceOfSale = provinceOfSale;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getStoreTitle() {
        return storeTitle;
    }

    public String getProvinceOfSale() {
        return provinceOfSale;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setCodeMely(String codeMely) {
        this.codeMely = codeMely;
    }

    public String getCodeMely() {
        return codeMely;
    }

    public Seller() {
        sellerKala = new ArrayList<>();
        wallet = new Wallet();
        orders = new ArrayList<>();
    }

    public Seller(String firstname, String lastname, String storeTitle, String codeMely, String phonenumber,
            String password, String provinceOfSale) {
        setFirstname(firstname);
        setLastname(lastname);
        setStoreTitle(storeTitle);
        setCodeMely(codeMely);
        setPhonenumber(phonenumber);
        setPassword(password);
        setProvinceOfSale(provinceOfSale);
        sellerKala = new ArrayList<>();
        wallet = new Wallet();
        orders = new ArrayList<>();
    }

    public int sellPerformance() {
        LocalDateTime loDTime = LocalDateTime.now().minusMonths(1);
        int price = 0;
        for (Transaction transaction : this.wallet.getTransactions()) {
            if (transaction.getLocalDateTime().isAfter(loDTime) && "Sale".equals(transaction.getWhy())) {
                price += transaction.getPrice();
            }
        }
        return price;
    }

    public void seeKala(Scanner scanner) {
        while (true) {
            Paginator<Kala> paginator = new Paginator<>(sellerKala, 10);
            int select = paginator.paginate(0);
            if (select == -1) {
                return;
            }
            Color.printWhiteBold(kalas.get(select) + " inventory: " + kalas.get(select).getInventory());
            Color.printYellow("Do you want to change the inventory? 1>YES 2>NO(Default)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("New number: ");
                    choice = scanner.nextLine();
                    if (!isInteger(choice) || Integer.parseInt(choice) < 0) {
                        Color.printRed("The format or number is incorrect.");
                    } else {
                        for (Kala kala : kalas) {
                            if (kala.equals(sellerKala.get(select))) {
                                kala.setInventory(Integer.parseInt(choice));
                            }
                        }
                        sellerKala.get(select).setInventory(Integer.parseInt(choice));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void addKala(Scanner scanner) {
        boolean runType = true, runDigital = true;
        while (runType) {
            System.out.print("Adding kala>>\n" +
                    "What type of product are you interested in?\n1) Digital Goods\n2) Book\n3) Back\n4) Exit\n => ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    while (runDigital) {
                        System.out.print("Adding model>>\n" +
                                "What type of Digital Goods are you interested in?\n1) Laptop\n2) Mobile\n3) Back\n4) Exit\n => ");
                        choice = scanner.nextLine();
                        switch (choice) {
                            case "1" -> addLaptop(scanner);
                            case "2" -> addMobile(scanner);
                            case "3" -> runDigital = false;
                            case "4" -> ExitVendilo.exit(scanner);
                            default -> System.out.println("The selected option is invalid.");
                        }
                    }
                }
                case "2" -> addBook(scanner);
                case "3" -> runType = false;
                case "4" -> ExitVendilo.exit(scanner);
                default -> System.out.println("The selected option is invalid.");
            }
        }
    }

    public void addBook(Scanner scanner) {
        Book book = new Book();
        while (true) {
            System.out.println("adding Book:");
            if (runBack(scanner) == 1) {
                return;
            }
            System.out.println("Enter name : ");
            book.setName(scanner.nextLine());
            System.out.println("Enter amount : ");
            String amount = scanner.nextLine();
            if (isInteger(amount) && Integer.parseInt(amount) >= 0) {
                book.setInventory(Integer.parseInt(amount));
            } else {
                continue;
            }
            System.out.println("Enter price : ");
            String price = scanner.nextLine();
            if (isInteger(price) && Integer.parseInt(price) > 0) {
                book.setPrice(Integer.parseInt(price));
            } else {
                continue;
            }
            kalas.add(addBook2(book, scanner));
            sellerKala.add(book);
            System.out.println("The product was added successfully.\n");
            return;
        }
    }

    public Book addBook2(Book book, Scanner scanner) {
        System.out.println("Enter author name : ");
        book.setAuthorsName(scanner.nextLine());
        while (true) {
            System.out.println("Enter number of pages : ");
            String pages = scanner.nextLine();
            if (isInteger(pages) && Integer.parseInt(pages) > 0) {
                book.setNumberOfPages(pages);
                break;
            } else {
                continue;
            }
        }
        addAge(book, scanner);
        System.out.println("Enter ISBN id : ");
        book.setIdISBN(scanner.nextLine());
        book.setSelerInfo((getFirstname() + " " + getLastname()), getAgencyCode(), getProvinceOfSale());
        return book;
    }

    public void addAge(Book book, Scanner scanner) {
        while (true) {
            System.out.println("adding Age:");
            if (runBack(scanner) == 1) {
                return;
            }
            System.out.println("Enter age category :\n1)CHILD\n2)ADOLESCENT\n3)ADULT");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    book.setAgeCategory(Age.CHILD);
                    return;
                case "2":
                    book.setAgeCategory(Age.ADOLESCENT);
                    return;
                case "3":
                    book.setAgeCategory(Age.ADULT);
                    return;
                default:
                    System.out.println("Choose the correct option.");
                    break;
            }
        }
    }

    public void addMobile(Scanner scanner) {
        Mobile mobile = new Mobile();
        while (true) {
            System.out.println("adding Mobile:");
            if (runBack(scanner) == 1) {
                return;
            }
            System.out.println("Enter name : ");
            mobile.setName(scanner.nextLine());
            System.out.println("Enter amount : ");
            String amount = scanner.nextLine();
            if (isInteger(amount) && Integer.parseInt(amount) >= 0) {
                mobile.setInventory(Integer.parseInt(amount));
            } else {
                continue;
            }

            kalas.add(addMobile2(mobile, scanner));
            sellerKala.add(mobile);
            System.out.println("The product was added successfully.\n");
            return;
        }
    }

    public Mobile addMobile2(Mobile mobile, Scanner scanner) {
        while (true) {
            System.out.println("Enter price : ");
            String price = scanner.nextLine();
            if (isInteger(price) && Integer.parseInt(price) > 0) {
                mobile.setPrice(Integer.parseInt(price));
                break;
            }
        }
        System.out.println("Enter brand : ");
        mobile.setBrand(scanner.nextLine());
        System.out.println("Enter internal memory size : ");
        mobile.setInternalMemorySize(scanner.nextLine());
        System.out.println("Enter amount of RAM : ");
        mobile.setAmountOfRAM(scanner.nextLine());
        System.out.println("Enter rear camera resolution : ");
        mobile.setRearCameraResolution(scanner.nextLine());
        System.out.println("Enter front camera resolution : ");
        mobile.setFrontCameraResolution(scanner.nextLine());
        addNetwork(mobile, scanner);
        mobile.setSelerInfo((getFirstname() + " " + getLastname()), getAgencyCode(), getProvinceOfSale());
        return mobile;
    }

    private void addNetwork(Mobile mobile, Scanner scanner) {
        while (true) {
            System.out.println("adding Network:");
            if (runBack(scanner) == 1) {
                return;
            }
            System.out.println("Enter internet network :\n1) 3G\n2) 4G\n3) 5G");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    mobile.setInternetNetwork("3G");
                    return;
                case "2":
                    mobile.setInternetNetwork("4G");
                    return;
                case "3":
                    mobile.setInternetNetwork("5G");
                    return;
                default:
                    System.out.println("Choose the correct option.");
                    break;
            }
        }
    }

    public void addLaptop(Scanner scanner) {
        Laptop laptop = new Laptop();
        while (true) {
            System.out.println("adding laptop:");
            if (runBack(scanner) == 1) {
                return;
            }
            System.out.println("Enter name : ");
            laptop.setName(scanner.nextLine());
            System.out.println("Enter amount : ");
            String amount = scanner.nextLine();
            if (isInteger(amount) && Integer.parseInt(amount) >= 0) {
                laptop.setInventory(Integer.parseInt(amount));
            } else {
                continue;
            }
            System.out.println("Enter price : ");
            String price = scanner.nextLine();
            if (isInteger(price) && Integer.parseInt(price) > 0) {
                laptop.setPrice(Integer.parseInt(price));
            } else {
                continue;
            }
            kalas.add(addLaptop2(laptop, scanner));
            sellerKala.add(laptop);
            System.out.println("The product was added successfully.\n");
            return;
        }
    }

    public Laptop addLaptop2(Laptop laptop, Scanner scanner) {
        System.out.println("Enter brand : ");
        laptop.setBrand(scanner.nextLine());
        System.out.println("Enter internal memory size : ");
        laptop.setInternalMemorySize(scanner.nextLine());
        System.out.println("Enter amount of RAM : ");
        laptop.setAmountOfRAM(scanner.nextLine());
        System.out.println("Enter graphics processor : ");
        laptop.setGraphicsProcessor(scanner.nextLine());
        addWebcam(laptop, scanner);
        addBlu(laptop, scanner);
        laptop.setSelerInfo((getFirstname() + " " + getLastname()), getAgencyCode(), getProvinceOfSale());
        return laptop;
    }

    private void addBlu(Laptop laptop, Scanner scanner) {
        while (true) {
            System.out.println("adding bluetooth:");
            if (runBack(scanner) == 1) {
                return;
            }
            System.out.println("Does the laptop have a bluetooth?\n1) YES\n2) NO");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    laptop.setBluetooth(true);
                    return;
                case "2":
                    laptop.setBluetooth(true);
                    return;
                default:
                    System.out.println("Choose the correct option.");
                    break;
            }
        }
    }

    private void addWebcam(Laptop laptop, Scanner scanner) {
        while (true) {
            System.out.println("adding Webcam:");
            if (runBack(scanner) == 1) {
                return;
            }
            System.out.println("Does the laptop have a webcam?\n1) YES\n2) NO");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    laptop.setWebcam(true);
                    return;
                case "2":
                    laptop.setWebcam(false);
                    return;
                default:
                    System.out.println("Choose the correct option.");
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return (super.toString() + " Store Title:" + getStoreTitle() + " CodeMely=>" + getCodeMely());
    }

    public String chap() {
        return "Name: " + getFirstname() + " " + getLastname() + " Email: " + getEmail() + " Agency Code: "
                + getAgencyCode();
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
