package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Vendilo {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Customer> customers = new ArrayList<>();
    private static List<Seller> sellers = new ArrayList<>();

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static List<Seller> getSellers() {
        return sellers;
    }

    public static void main(String[] args) {
        boolean runVENDILO = true;
        // Customer a = new Customer();
        // a.setFirstname("mmd");
        // System.out.println(a.getFirstname());
        // customers.add(a);
        System.out.println("Welcome to the VENDILO.");
        while (runVENDILO) {
            // System.out.println("\n");
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.print("Please enter the desired option :");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("|       |       |\n|       |       |\n|       |       |\n|       |       |");
                    boolean runLogin = true;
                    while (runLogin) {
                        System.out.println("1 - Customer Login");
                        System.out.println("Press any key to go back.");
                        System.out.print("Please enter the desired option :");
                        String choiceLogin = scanner.nextLine();
                        switch (choiceLogin) {
                            case "1":
                                Customer c = customerLogin();
                                break;

                            default:
                                runLogin = false;
                                break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("|       |       |\n|       |       |\n|       |       |\n|       |       |");
                    boolean runRegister = true;
                    while (runRegister) {
                        System.out.println("1 - Customer Register");
                        System.out.println("Press any key to go back.");
                        System.out.print("Please enter the desired option :");
                        int choiceLogin = scanner.nextInt();
                        switch (choiceLogin) {
                            case 1:
                                customerRegister();
                                break;

                            default:
                                runRegister = false;
                                break;
                        }
                    }
                    break;

                default:
                    runVENDILO = false;
                    break;
            }
        }
        System.out.println("We look forward to seeing you again.");
    }

    // public static void searchKala() {
    // System.out.println("- - - - Search Kala - - - -\nEnter at least one of the
    // following fields\nProduct Name: ");
    // String productName = scanner.nextLine();
    // String productType = searchForProductType();
    // System.out.println("Enter price range");
    // int min = priceRange("min");
    // int max = priceRange("max");
    // List<Kala> searchingKala = new ArrayList<>();
    // for (Kala kala : Seller.getKalas()) {
    // if (kala.getPrice() <= max && kala.getPrice() >= min) {
    // if (kala.getName().contains(productName)) {
    // if (!productType.equalsIgnoreCase("all")) {
    // if (kala instanceof Book && productType.equalsIgnoreCase("book")) {
    // searchingKala.add(kala);
    // } else {
    // if (kala instanceof DigitalGoods && productType.equalsIgnoreCase("Digital
    // Goods")) {
    // searchingKala.add(kala);
    // } else {
    // if (kala instanceof Mobile && productType.equalsIgnoreCase("mobile")) {
    // searchingKala.add(kala);
    // } else {
    // if (kala instanceof Laptop && productName.equalsIgnoreCase("laptop")) {
    // searchingKala.add(kala);
    // }
    // }
    // }
    // }
    // } else {
    // searchingKala.add(kala);
    // }
    // }
    // }
    // }
    // showList(searchingKala, productType);
    // }

    // public static void showList(List<Kala> searchingKala, String productType) {
    // while (true) {
    // System.out.println(
    // "How would you like the products to be displayed?\n1)From the highest
    // price\n2)From the lowest price\n3)Normal\nPress 0 to go back.");
    // String choice = scanner.nextLine();
    // switch (choice) {
    // case "0":
    // return;
    // case "1":
    // upSortList(searchingKala);
    // break;
    // case "2":
    // downSortList(searchingKala);
    // break;
    // case "3":
    // break;
    // default:
    // break;
    // }
    // for (int i = 0; i < searchingKala.size(); i++) {
    // System.out.println(
    // (i + 1) + ")" + searchingKala.get(i).getName() + productType +
    // searchingKala.get(i).getPrice());
    // }
    // productSelection(searchingKala, productType);
    // }
    // }

    // public static void productSelection(List<Kala> searchingKala, String
    // productType) {
    // while (true) {

    // System.out.println("Select the product you want: ");
    // System.out.println("Press 0 to go back.");
    // String choice = scanner.nextLine();
    // switch (choice) {
    // case "0":
    // return;
    // default:
    // if (isInteger(choice)) {
    // int ran = Integer.parseInt(choice) - 1;
    // if (ran >= 0 && ran < searchingKala.size()) {
    // displayInformationAboutTheSelectedProduct(searchingKala.get(ran),
    // productType);
    // } else {
    // System.out.println("Incorrect input. Enter a number.");
    // }
    // }
    // break;
    // }
    // }
    // }

    // private static void displayInformationAboutTheSelectedProduct(Kala kala,
    // String productType) {
    // Seller a = new Seller();
    // for (Seller seller : sellers) {
    // for (Kala kala2 : seller.getSellerKala()) {
    // if (kala2.equals(kala)) {
    // a = seller;
    // }
    // }
    // }
    // System.out.println(kala.getName() + productType + a.getFirstname() +
    // a.getLastname() + kala.getAverageScore()
    // + a.getProvinceOfSale());
    // addToCart(a, kala);
    // }

    // private static void addToCart(Seller a, Kala kala) {
    // System.out.println("Do you want to add this item to your
    // cart?\n1)YES\n2)NO");
    // String choice = scanner.nextLine();
    // switch (choice) {
    // case "1":

    // break;
    // default:
    // return;
    // }
    // }

    // public static void upSortList(List<Kala> searchingKala) {
    // searchingKala = searchingKala.stream()
    // .sorted(Comparator.comparingInt(Kala::getPrice))
    // .collect(Collectors.toList());
    // }

    // public static void downSortList(List<Kala> searchingKala) {
    // searchingKala = searchingKala.stream()
    // .sorted(Comparator.comparingInt(Kala::getPrice).reversed())
    // .collect(Collectors.toList());
    // }

    // public static String searchForProductType() {
    // System.out.println("Product Type:\n1)Book\n2)Digital Goods\n3)ALL");
    // String choice = scanner.nextLine();
    // switch (choice) {
    // case "1":
    // return "Book";
    // case "2":
    // System.out.println("Digital Goods Type:\n1)Mobile\n2)Laptop\n3)ALL");
    // choice = scanner.nextLine();
    // switch (choice) {
    // case "1":
    // return "Mobile";
    // case "2":
    // return "Laptop";
    // default:
    // return "Digital Goods";
    // }
    // default:
    // return "ALL";
    // }
    // }

    // public static int priceRange(String val) {
    // while (true) {
    // System.out.println(val + ": ");
    // String range = scanner.nextLine();
    // if (range == null) {
    // if (val.equalsIgnoreCase("max")) {
    // int ran = Integer.MAX_VALUE;
    // return ran;
    // } else {
    // if (val.equalsIgnoreCase("min")) {
    // int ran = Integer.MIN_VALUE;
    // return ran;
    // }
    // }
    // } else {
    // if (isInteger(range)) {
    // int ran = Integer.parseInt(range);
    // return ran;
    // } else {
    // System.out.println("Incorrect input. Enter a number.");
    // }
    // }
    // }
    // }

    public static Customer customerLogin() {
        System.out.println("- - - - customerLogin - - - -");
        System.out.println("Enter email :");
        String emailOrPhoneNumber = scanner.nextLine();
        System.out.println("Enter password :");
        String password = scanner.nextLine();
        boolean em = false, pas = false;

        for (Customer customer : customers) {
            if (customer.getEmail().equals(emailOrPhoneNumber)
                    || customer.getPhonenumber().equals(emailOrPhoneNumber)) {
                em = true;
                if (customer.getPassword().equals(password)) {
                    pas = true;
                    System.out.println("Successful login.");
                    return customer;
                }
            }
        }
        if (!em) {
            System.out.println("No user found with this email or phone number.");
            return null;
        }
        if (!pas) {
            System.out.println("No user found with this Password.");
            return null;
        }
        return null;
    }

    public static void customerRegister() {
        System.out.println("- - - - customerRegister - - - -");
        System.out.println("Enter firstname :");
        String firstname = scanner.nextLine();

        System.out.println("Enter lastname :");
        String lastname = scanner.nextLine();

        System.out.println("Enter email :");
        String email = scanner.nextLine();
        if (!chekEmail(email)) {
            return;
        }

        System.out.println("Enter phone number :");
        String phonenumber = scanner.nextLine();
        if (!chekPhonenumber(phonenumber)) {
            return;
        }

        System.out.println("Enter password :");
        String password = scanner.nextLine();
        if (!chekPassword(password)) {
            return;
        }

        Customer a = new Customer(firstname, lastname, email, phonenumber, password);
        customers.add(a);
        System.out.println("Registration was successful.");
        customerLogin();
    }

    public static void sellerRegister() {
        System.out.println("- - - - sellerRegister - - - -");
        System.out.println("Enter firstname :");
        String firstname = scanner.nextLine();

        System.out.println("Enter lastname :");
        String lastname = scanner.nextLine();

        System.out.println("Enter Store Title :");
        String storeTitle = scanner.nextLine();
        if (!chekStoreTitle(storeTitle)) {
            return;
        }

        System.out.println("Enter Code Mely :");
        String codeMely = scanner.nextLine();
        if (!chekCodeMely(codeMely)) {
            return;
        }

        System.out.println("Enter phone number :");
        String phonenumber = scanner.nextLine();
        if (!chekPhonenumber(phonenumber)) {
            return;
        }

        System.out.println("Enter password :");
        String password = scanner.nextLine();
        if (!chekPassword(password)) {
            return;
        }

        System.out.println("Enter Province Of Sale :");
        String provinceOfSale = scanner.nextLine();

        Seller a = new Seller(firstname, lastname, storeTitle, codeMely, phonenumber, password, provinceOfSale);
        a.setAgencyCode(generateAgencyCode());
        sellers.add(a);

        sellerLogin();
    }

    private static Seller sellerLogin() {
        System.out.println("Enter Agency code :");
        String agencyCode = scanner.nextLine();
        System.out.println("Enter password :");
        String password = scanner.nextLine();
        boolean co = false, pas = false;

        for (Seller seller : sellers) {
            if (seller.getAgencyCode().equals(agencyCode)) {
                co = true;
                if (seller.getPassword().equals(password)) {
                    pas = true;
                    System.out.println("Successful login.");
                    return seller;
                }
            }
        }
        if (!co) {
            System.out.println("No user found with this Agency code.");
            return null;
        }
        if (!pas) {
            System.out.println("No user found with this Password.");
            return null;
        }
        return null;
    }

    public static boolean chekEmail(String email) {
        String regex = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            System.out.println("The email format is incorrect.");
            return false;
        } else {
            for (Customer customer : customers) {
                if (customer.getEmail().equals(email)) {
                    System.out.println("The email is duplicate.");
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean chekPhonenumber(String phonenumber) {
        String regex = "^(\\+98|0)?9\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phonenumber);
        if (!matcher.find()) {
            System.out.println("The phone number format is incorrect.");
            return false;
        } else {
            for (Customer customer : customers) {
                if (customer.getPhonenumber().equals(phonenumber)) {
                    System.out.println("The phone number is duplicate.");
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean chekPassword(String password) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            System.out.println("The password format is incorrect.");
            return false;
        } else {
            return true;
        }
    }

    public static boolean chekStoreTitle(String storeTitle) {
        for (Seller seller : sellers) {
            if (seller.getStoreTitle().equals(storeTitle)) {
                System.out.println("The Store Title is duplicate.");
                return false;
            }
        }
        return true;
    }

    public static boolean chekCodeMely(String codeMely) {
        for (Seller seller : sellers) {
            if (seller.getCodeMely().equals(codeMely)) {
                System.out.println("The Code Mely is duplicate.");
                return false;
            }
        }
        return true;
    }

    public static String generateAgencyCode() {
        String alpha = "QWE0RT789YUIOPAS156DFGHJKLZXC234VBNM";
        Random random = new Random();
        String ramz = new String();
        boolean duplicate = true;
        while (duplicate) {
            for (int i = 0; i < 6; i++) {
                int randInt = random.nextInt(36);
                ramz.concat(alpha.substring(randInt, randInt + 1));
            }
            duplicate = false;
            for (Seller seller : sellers) {
                if (seller.getAgencyCode().equals(ramz)) {
                    duplicate = true;
                }
            }
        }
        System.out.println("This is your Agency code : " + ramz);
        return ramz;
    }

    // public static boolean isInteger(String str) {
    // try {
    // Integer.parseInt(str);
    // return true;
    // } catch (NumberFormatException e) {
    // return false;
    // }
    // }
}