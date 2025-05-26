package ir.ac.kntu;

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

    public void addKala(Scanner scanner) {
        boolean runType = true, runDigital = true;
        while (runType) {
            System.out.print("Adding kala\n>>" +
                    "What type of product are you interested in?\n1) Digital Goods\n2) Book\n3)Back\n4)Exit\n => ");
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
            if (isInteger(amount)) {
                book.setInventory(Integer.parseInt(amount));
            } else {
                continue;
            }
            System.out.println("Enter price : ");
            String price = scanner.nextLine();
            if (isInteger(price)) {
                book.setPrice(Integer.parseInt(price));
            } else {
                continue;
            }
            kalas.add(addBook2(book, scanner));
            sellerKala.add(book);
            return;
        }
    }

    public Book addBook2(Book book, Scanner scanner) {
        System.out.println("Enter author name : ");
        book.setAuthorsName(scanner.nextLine());
        System.out.println("Enter number of pages : ");
        book.setNumberOfPages(scanner.nextLine());
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
            if (isInteger(amount)) {
                mobile.setInventory(Integer.parseInt(amount));
            } else {
                continue;
            }
            System.out.println("Enter price : ");
            String price = scanner.nextLine();
            if (isInteger(price)) {
                mobile.setPrice(Integer.parseInt(price));
            } else {
                continue;
            }
            System.out.println("Enter brand : ");
            mobile.setBrand(scanner.nextLine());
            kalas.add(addMobile2(mobile, scanner));
            sellerKala.add(mobile);
            return;
        }
    }

    public Mobile addMobile2(Mobile mobile, Scanner scanner) {
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
            if (isInteger(amount)) {
                laptop.setInventory(Integer.parseInt(amount));
            } else {
                continue;
            }
            System.out.println("Enter price : ");
            String price = scanner.nextLine();
            if (isInteger(price)) {
                laptop.setPrice(Integer.parseInt(price));
            } else {
                continue;
            }
            kalas.add(addLaptop2(laptop, scanner));
            sellerKala.add(laptop);
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
        // Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("adding bluetooth:");
            if (runBack(scanner) == 1) {
                // scanner.close();
                return;
            }
            System.out.println("Does the laptop have a bluetooth?\n1) YES\n2) NO");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    laptop.setBluetooth(true);
                    // scanner.close();
                    return;
                case "2":
                    laptop.setBluetooth(true);
                    // scanner.close();
                    return;
                default:
                    System.out.println("Choose the correct option.");
                    break;
            }
        }
    }

    private void addWebcam(Laptop laptop, Scanner scanner) {
        // Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("adding Webcam:");
            if (runBack(scanner) == 1) {
                // scanner.close();
                return;
            }
            System.out.println("Does the laptop have a webcam?\n1) YES\n2) NO");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    laptop.setWebcam(true);
                    // scanner.close();
                    return;
                case "2":
                    laptop.setWebcam(false);
                    // scanner.close();
                    return;
                default:
                    System.out.println("Choose the correct option.");
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return (" Store Title:" + getStoreTitle() + " CodeMely=>" + getCodeMely());
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
