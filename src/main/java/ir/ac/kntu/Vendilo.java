package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vendilo {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Seller> sellers = new ArrayList<>();
    private static List<Seller> sellersVerification = new ArrayList<>();
    private static List<Supporter> supporters = new ArrayList<>();

    public Vendilo() {

    }

    public static List<Supporter> getSupporters() {
        return supporters;
    }

    public static void setSupporters(List<Supporter> supporters) {
        Vendilo.supporters = supporters;
    }

    public static void setCustomers(List<Customer> customers) {
        Vendilo.customers = customers;
    }

    public static void setSellers(List<Seller> sellers) {
        Vendilo.sellers = sellers;
    }

    public static List<Seller> getSellersVerification() {
        return sellersVerification;
    }

    public static void setSellersVerification(List<Seller> sellersVerification) {
        Vendilo.sellersVerification = sellersVerification;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static List<Seller> getSellers() {
        return sellers;
    }

    public void menu() {
        Supporter supporter = new Supporter("sajad", "teymoori", "sajadilo", "12345Aa!");
        supporters.add(supporter);
        Scanner scanner = new Scanner(System.in);
        boolean runVENDILO = true;
        System.out.println("Welcome to the VENDILO.");
        while (runVENDILO) {
            System.out.println("1-Login\n2-Register\n3-Exit");
            System.out.print("Please enter the desired option :");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> loginMenu();
                case "2" -> registerMenu();
                case "3" -> runVENDILO = false;
                default -> System.out.println("The selected option is invalid.");
            }
        }
        scanner.close();
        System.out.println("We look forward to seeing you again.");
    }

    public static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean runLogin = true;
        while (runLogin) {
            System.out.println("1-Customer Login\n2-Seller Login\n3-Supporter Login\n4-Back\n5-Exit");
            System.out.print("Please enter the desired option :");
            String choiceLogin = scanner.nextLine();
            switch (choiceLogin) {
                case "1" -> customerLogin();
                case "2" -> sellerLogin();
                case "3" -> supporterLogin();
                case "4" -> runLogin = false;
                case "5" -> System.exit(0);
                default -> System.out.println("The selected option is invalid.");
            }
        }
    }

    public static void supporterLogin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("- - - - SupporterLogin - - - -");
            if (runBack() == 1) {
                break;
            }
            System.out.print("Enter Agency Name :");
            String agencyName = scanner.nextLine();
            System.out.print("Enter password :");
            String password = scanner.nextLine();
            boolean em = false, pas = false;
            for (Supporter supporter : supporters) {
                if (supporter.getAgencyName().equals(agencyName)) {
                    em = true;
                    if (supporter.getPassword().equals(password)) {
                        pas = true;
                        System.out.println("Successful login.");
                        SupporterHelper supporterHelper = new SupporterHelper(supporter);
                        supporterHelper.menu();
                    }
                }
            }
            if (!em) {
                System.out.println("No user found with this email or phone number.");
                continue;
            }
            if (!pas) {
                System.out.println("No user found with this Password.");
                continue;
            }
        }
        // scanner.close();
    }

    public static void registerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean runRegister = true;
        while (runRegister) {
            System.out.println("1-Customer Register\n2-Seller Register\n3-Back\n4-Exit");
            System.out.print("Please enter the desired option :");
            String choiceLogin = scanner.nextLine();
            switch (choiceLogin) {
                case "1" -> customerRegister();
                case "2" -> sellerRegister();
                case "3" -> runRegister = false;
                case "4" -> System.exit(0);
                default -> System.out.println("The selected option is invalid.");
            }
        }
    }

    public static void customerLogin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("- - - - customerLogin - - - -");
            if (runBack() == 1) {
                break;
            }
            System.out.print("Enter email or phone number :");
            String emailOrPhoneNumber = scanner.nextLine();
            System.out.print("Enter password :");
            String password = scanner.nextLine();
            boolean em = false, pas = false;
            for (Customer customer : customers) {
                if (customer.getEmail().equals(emailOrPhoneNumber)
                        || customer.getPhonenumber().equals(emailOrPhoneNumber)) {
                    em = true;
                    if (customer.getPassword().equals(password)) {
                        pas = true;
                        System.out.println("Successful login.");
                        // scanner.close();
                        CustomerHelper a = new CustomerHelper(customer);
                        a.menu();
                    }
                }
            }
            if (!em) {
                System.out.println("No user found with this email or phone number.");
                continue;
            }
            if (!pas) {
                System.out.println("No user found with this Password.");
                continue;
            }
        }
        // scanner.close();
    }

    public static void customerRegister() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("- - - - customerRegister - - - -");
            if (runBack() == 1) {
                break;
            }
            System.out.print("Enter firstname :");
            String firstname = scanner.nextLine();
            System.out.print("\nEnter lastname :");
            String lastname = scanner.nextLine();
            System.out.print("\nEnter email :");
            String email = scanner.nextLine();
            if (!chekEmail(email)) {
                continue;
            }
            System.out.print("\nEnter phone number :");
            String phonenumber = scanner.nextLine();
            if (!chekPhonenumber(phonenumber)) {
                continue;
            }
            System.out.print("\nEnter password :");
            String password = scanner.nextLine();
            if (!chekPassword(password)) {
                continue;
            }
            // scanner.close();
            Customer a = new Customer(firstname, lastname, email, phonenumber, password);
            customers.add(a);
            System.out.println("Registration was successful.");
            break;
        }
        // scanner.close();
    }

    public static void sellerRegister() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("- - - - seller Register - - - -");
            if (runBack() == 1) {
                break;
            }
            System.out.print("Enter firstname :");
            String firstname = scanner.nextLine();
            System.out.print("Enter lastname :");
            String lastname = scanner.nextLine();
            System.out.print("Enter Store Title :");
            String storeTitle = scanner.nextLine();
            if (!chekStoreTitle(storeTitle)) {
                continue;
            }
            System.out.print("Enter Code Mely :");
            String codeMely = scanner.nextLine();
            if (!chekCodeMely(codeMely)) {
                continue;
            }
            System.out.print("Enter phone number :");
            String phonenumber = scanner.nextLine();
            if (!chekPhonenumber(phonenumber)) {
                continue;
            }
            System.out.print("Enter password :");
            String password = scanner.nextLine();
            if (!chekPassword(password)) {
                continue;
            }
            System.out.print("Enter Province Of Sale :");
            String provinceOfSale = scanner.nextLine();
            Seller a = new Seller(firstname, lastname, storeTitle, codeMely, phonenumber, password, provinceOfSale);
            a.setAgencyCode(generateAgencyCode());
            a.setReasonForRejection("Your authentication has not been confirmed yet.");
            Supporter.getSellersVerification().add(a);
            sellersVerification.add(a);
            break;
        }
        // scanner.close();
    }

    private static void sellerLogin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("- - - - seller Login - - - -");
            if (runBack() == 1) {
                break;
            }
            System.out.println("Enter Agency code :");
            String agencyCode = scanner.nextLine();
            System.out.println("Enter password :");
            String password = scanner.nextLine();
            boolean co = false, pas = false, conti = false;
            for (Seller seller : sellersVerification) {
                if (seller.getAgencyCode().equals(agencyCode)) {
                    if (seller.getPassword().equals(password)) {
                        System.out.println(seller.getReasonForRejection());
                        conti = true;
                        break;
                    }
                }
            }
            if (conti) {
                continue;
            }
            for (Seller seller : sellers) {
                if (seller.getAgencyCode().equals(agencyCode)) {
                    co = true;
                    if (seller.getPassword().equals(password)) {
                        pas = true;
                        System.out.println("Successful login.");
                        // scanner.close();
                        SellerHelper sellerHelper = new SellerHelper(seller);
                        sellerHelper.menu();
                    }
                }
            }
            if (!co) {
                System.out.println("No user found with this Agency code.");
                continue;
            }
            if (!pas) {
                System.out.println("No user found with this Password.");
                continue;
            }
        }
        // scanner.close();
    }

    public static int runBack() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter the desired option : 1)Back 2)Exit 3)Continue");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // scanner.close();
                    return 1;
                case "2":
                    // scanner.close();
                    System.exit(0);
                case "3":
                    // scanner.close();
                    return 3;
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    public static boolean chekEmail(String email) {
        String regex = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            System.out.println("The email format is incorrect.\nTry again.");
            return false;
        } else {
            for (Customer customer : customers) {
                if (customer.getEmail().equals(email)) {
                    System.out.println("The email is duplicate.\nTry again.");
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
            System.out.println("The phone number format is incorrect.\nTry again.");
            return false;
        } else {
            for (Customer customer : customers) {
                if (customer.getPhonenumber().equals(phonenumber)) {
                    System.out.println("The phone number is duplicate.\nTry again.");
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
            System.out.println("The password format is incorrect.\nTry again.");
            return false;
        } else {
            return true;
        }
    }

    public static boolean chekStoreTitle(String storeTitle) {
        for (Seller seller : sellers) {
            if (seller.getStoreTitle().equals(storeTitle)) {
                System.out.println("The Store Title is duplicate.\nTry again.");
                return false;
            }
        }
        return true;
    }

    public static boolean chekCodeMely(String codeMely) {
        for (Seller seller : sellers) {
            if (seller.getCodeMely().equals(codeMely)) {
                System.out.println("The Code Mely is duplicate.\nTry again.");
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