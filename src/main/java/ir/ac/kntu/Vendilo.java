package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vendilo {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Customer> customers = new ArrayList<>();
    private static List<Seller> sellers = new ArrayList<>();
    private static List<Seller> sellersVerification = new ArrayList<>();

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

    public static void main(String[] args) {
        boolean runVENDILO = true;
        System.out.println("Welcome to the VENDILO.");
        while (runVENDILO) {
            System.out.println("1 - Login" + "\n2 - Register");
            System.out.print("Please enter the desired option :");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("|       |       |\n|       |       |\n|       |       |\n|       |       |");
                    boolean runLogin = true;
                    while (runLogin) {
                        System.out.println("1 - Customer Login" + "\n2 - Seller Login" + "");
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
        a.setReasonForRejection("Your authentication has not been confirmed yet.");
        Supporter.getSellersVerification().add(a);
        sellersVerification.add(a);

        sellerLogin();
    }

    private static Seller sellerLogin() {
        System.out.println("Enter Agency code :");
        String agencyCode = scanner.nextLine();
        System.out.println("Enter password :");
        String password = scanner.nextLine();
        boolean co = false, pas = false;
        for (Seller seller : sellersVerification) {
            if (seller.getAgencyCode().equals(agencyCode)) {
                if (seller.getPassword().equals(password)) {
                    System.out.println(seller.getReasonForRejection());
                    return null;
                }
            }
        }
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