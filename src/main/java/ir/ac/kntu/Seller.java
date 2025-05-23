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
    private String reasonForRejection;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getReasonForRejection() {
        return reasonForRejection;
    }

    public void setReasonForRejection(String reasonForRejection) {
        this.reasonForRejection = reasonForRejection;
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
    }

    public void menu() {

    }

    public void addKala() {
        Scanner scanner = new Scanner(System.in);
        boolean runType = true, runDigital = true;
        while (runType) {
            System.out.println(
                    "What type of product are you interested in?\n1) Digital Goods\n2) Book\nPress any key to go back.\nPlease enter the desired option :");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    while (runDigital) {
                        System.out.println(
                                "What type of Digital Goods are you interested in?\n1) Laptop\n2) Mobile\nPress any key to go back.\nPlease enter the desired option :");
                        choice = scanner.nextLine();
                        switch (choice) {
                            case "1":
                                addLaptop();
                                break;
                            case "2":
                                addMobile();
                                break;
                            default:
                                runDigital = false;
                                break;
                        }
                    }
                    break;
                case "2":
                    addBook();
                    break;
                default:
                    runType = false;
                    break;
            }
        }
        scanner.close();
    }

    public void addBook() {
        Book book = new Book();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name : ");
        book.setName(scanner.nextLine());
        System.out.println("Enter amount : ");
        book.setInventory(scanner.nextInt());
        System.out.println("Enter price : ");
        scanner.nextLine();
        book.setPrice(scanner.nextInt());
        System.out.println("Enter author name : ");
        scanner.nextLine();
        book.setAuthorsName(scanner.nextLine());
        System.out.println("Enter number of pages : ");
        book.setNumberOfPages(scanner.nextLine());
        addAge(book);
        System.out.println("Enter ISBN id : ");
        book.setIdISBN(scanner.nextLine());
        book.setSelerInfo((getFirstname() + " " + getLastname()), getAgencyCode(), getProvinceOfSale());
        kalas.add(book);
        sellerKala.add(book);
        scanner.close();
    }

    public void addAge(Book book) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter age category :\n1)CHILD\n2)ADOLESCENT\n3)ADULT\n");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    book.setAgeCategory(Age.CHILD);
                    scanner.close();
                    return;
                case "2":
                    book.setAgeCategory(Age.ADOLESCENT);
                    scanner.close();
                    return;
                case "3":
                    book.setAgeCategory(Age.ADULT);
                    scanner.close();
                    return;
                default:
                    System.out.println("Choose the correct option.");
                    break;
            }
        }
    }

    public void addMobile() {
        Mobile mobile = new Mobile();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name : ");
        mobile.setName(scanner.nextLine());
        System.out.println("Enter amount : ");
        mobile.setInventory(scanner.nextInt());
        System.out.println("Enter price : ");
        mobile.setPrice(scanner.nextInt());
        System.out.println("Enter brand : ");
        scanner.nextLine();
        mobile.setBrand(scanner.nextLine());
        System.out.println("Enter internal memory size : ");
        mobile.setInternalMemorySize(scanner.nextLine());
        System.out.println("Enter amount of RAM : ");
        mobile.setAmountOfRAM(scanner.nextLine());
        System.out.println("Enter rear camera resolution : ");
        mobile.setRearCameraResolution(scanner.nextLine());
        System.out.println("Enter front camera resolution : ");
        mobile.setFrontCameraResolution(scanner.nextLine());
        addNetwork(mobile);
        mobile.setSelerInfo((getFirstname() + " " + getLastname()), getAgencyCode(), getProvinceOfSale());
        kalas.add(mobile);
        sellerKala.add(mobile);
        scanner.close();
    }

    private void addNetwork(Mobile mobile) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter internet network :\n1) 3G\n2) 4G\n3) 5G\n");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    mobile.setInternetNetwork("3G");
                    scanner.close();
                    return;
                case "2":
                    mobile.setInternetNetwork("4G");
                    scanner.close();
                    return;
                case "3":
                    mobile.setInternetNetwork("5G");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choose the correct option.");
                    break;
            }
        }
    }

    public void addLaptop() {
        Laptop laptop = new Laptop();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name : ");
        laptop.setName(scanner.nextLine());
        System.out.println("Enter amount : ");
        laptop.setInventory(scanner.nextInt());
        System.out.println("Enter price : ");
        laptop.setPrice(scanner.nextInt());
        System.out.println("Enter brand : ");
        scanner.nextLine();
        laptop.setBrand(scanner.nextLine());
        System.out.println("Enter internal memory size : ");
        laptop.setInternalMemorySize(scanner.nextLine());
        System.out.println("Enter amount of RAM : ");
        laptop.setAmountOfRAM(scanner.nextLine());
        System.out.println("Enter graphics processor : ");
        laptop.setGraphicsProcessor(scanner.nextLine());
        addWebcam(laptop);
        addBlu(laptop);
        laptop.setSelerInfo((getFirstname() + " " + getLastname()), getAgencyCode(), getProvinceOfSale());
        kalas.add(laptop);
        sellerKala.add(laptop);
        scanner.close();
    }

    private void addBlu(Laptop laptop) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Does the laptop have a bluetooth?\n1) YES\n2) NO\n");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    laptop.setBluetooth(true);
                    scanner.close();
                    return;
                case "2":
                    laptop.setBluetooth(true);
                    ;
                    scanner.close();
                    return;
                default:
                    System.out.println("Choose the correct option.");
                    break;
            }
        }
    }

    private void addWebcam(Laptop laptop) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Does the laptop have a webcam?\n1) YES\n2) NO\n");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    laptop.setWebcam(true);
                    scanner.close();
                    return;
                case "2":
                    laptop.setWebcam(false);
                    scanner.close();
                    return;
                default:
                    System.out.println("Choose the correct option.");
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return ("Name: " + getFirstname() + " " + getLastname() + " Store Title:" + getStoreTitle() + " Email:"
                + getEmail() + " Agency Code:" + getAgencyCode());
    }
}
