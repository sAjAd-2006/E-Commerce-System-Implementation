package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vendilo {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Seller> sellers = new ArrayList<>();
    private static List<Seller> verification = new ArrayList<>();
    private static List<Supporter> supporters = new ArrayList<>();

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
        return verification;
    }

    public static void setSellersVerification(List<Seller> verification) {
        Vendilo.verification = verification;
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
        ChekFild chekFild = new ChekFild(sellers, customers);
        Color.printCyanBold("Welcome to the VENDILO.");
        while (true) {
            Color.printWhiteBold("\nMain Menu:");
            Color.printGreen("1-Login");
            Color.printGreen("2-Register");
            Color.printGreen("3-Exit");
            Color.printYellowInline("Please enter the desired option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> loginMenu(scanner);
                case "2" -> registerMenu(scanner, chekFild);
                case "3" -> ExitVendilo.exit(scanner);
                default -> {
                    Color.printRedBgWhite("The selected option is invalid!");
                    Color.printRed("Please try again.");
                }
            }
        }
    }

    public void loginMenu(Scanner scanner) {
        boolean runLogin = true;
        while (runLogin) {
            Color.printWhiteBold("\nLogin Menu:");
            Color.printCyan("1-Customer Login");
            Color.printCyan("2-Seller Login");
            Color.printCyan("3-Supporter Login");
            Color.printYellow("4-Back");
            Color.printRed("5-Exit");
            Color.printYellowInline("Please enter the desired option: ");
            String choiceLogin = scanner.nextLine();
            switch (choiceLogin) {
                case "1" -> customerLogin(scanner);
                case "2" -> sellerLogin(scanner);
                case "3" -> supporterLogin(scanner);
                case "4" -> runLogin = false;
                case "5" -> {
                    Color.printBlue("Exiting application...");
                    ExitVendilo.exit(scanner);
                }
                default -> {
                    Color.printRedBgWhite("The selected option is invalid!");
                    Color.printRed("Please enter a number between 1-5.");
                }
            }
        }
    }

    public void supporterLogin(Scanner scanner) {
        while (true) {
            Color.printWhiteBold("- - - - Supporter Login - - - -");
            if (runBack(scanner) == 1) {
                break;
            }
            Color.printYellowInline("Enter Agency Name: ");
            String agencyName = scanner.nextLine();
            Color.printYellowInline("Enter password: ");
            String password = scanner.nextLine();
            supporterLogin2(agencyName, password, scanner);
        }
    }

    public void supporterLogin2(String agencyName, String password, Scanner scanner) {
        boolean email = false, pas = false;
        for (Supporter supporter : supporters) {
            if (supporter.getAgencyName().equals(agencyName)) {
                email = true;
                if (supporter.getPassword().equals(password)) {
                    pas = true;
                    Color.printGreen("Successful login.");
                    SupporterHelper supporterHelper = new SupporterHelper(supporter);
                    supporterHelper.menu(scanner);
                }
            }
        }
        if (!email) {
            Color.printRed("No user found with this email or phone number.");
            return;
        }
        if (!pas) {
            Color.printRed("No user found with this Password.");
            return;
        }
    }

    public void registerMenu(Scanner scanner, ChekFild chekFild) {
        boolean runRegister = true;
        while (runRegister) {
            Color.printWhiteBold("\nRegistration Menu:");
            Color.printCyan("1-Customer Register");
            Color.printCyan("2-Seller Register");
            Color.printYellow("3-Back");
            Color.printRed("4-Exit");

            Color.printYellowInline("Please enter the desired option: ");

            String choiceLogin = scanner.nextLine();
            switch (choiceLogin) {
                case "1" -> customerRegister(scanner, chekFild);
                case "2" -> sellerRegister(scanner, chekFild);
                case "3" -> runRegister = false;
                case "4" -> {
                    ExitVendilo.exit(scanner);
                }
                default -> {
                    Color.printRedBgWhite("The selected option is invalid!");
                    Color.printRed("Please enter a number between 1-4.");
                }
            }
        }
    }

    public void customerLogin(Scanner scanner) {
        while (true) {
            Color.printWhiteBold("- - - - Customer Login - - - -");
            if (runBack(scanner) == 1) {
                break;
            }
            Color.printYellowInline("Enter email or phone number: ");
            String emailOrPhone = scanner.nextLine();
            Color.printYellowInline("Enter password: ");
            String password = scanner.nextLine();
            customerLogin2(emailOrPhone, password, scanner);
        }
    }

    public void customerLogin2(String emailOrPhone, String password, Scanner scanner) {
        boolean emailEror = false, passwordEror = false;
        for (Customer customer : customers) {
            if (customer.getEmail().equals(emailOrPhone)
                    || customer.getPhonenumber().equals(emailOrPhone)) {
                emailEror = true;
                if (customer.getPassword().equals(password)) {
                    passwordEror = true;
                    Color.printGreen("Successful login.");
                    CustomerHelper customerHelper = new CustomerHelper(customer);
                    customerHelper.menu(scanner);
                }
            }
        }
        if (!emailEror) {
            Color.printRed("No user found with this email or phone number.");
            return;
        }
        if (!passwordEror) {
            Color.printRed("No user found with this Password.");
            return;
        }
    }

    public void customerRegister(Scanner scanner, ChekFild chekFild) {
        while (true) {
            if (runBack(scanner) == 1) {
                break;
            }
            Color.printWhiteBold("- - - - Customer Register - - - -");
            Color.printYellowInline("Enter firstname: ");
            String firstname = scanner.nextLine();
            Color.printYellowInline("Enter lastname: ");
            String lastname = scanner.nextLine();
            Color.printYellowInline("Enter email: ");
            String email = scanner.nextLine();
            if (!chekFild.chekEmail(email)) {
                continue;
            }
            Color.printYellowInline("Enter phone number: ");
            String phonenumber = scanner.nextLine();
            if (!chekFild.chekPhonenumber(phonenumber)) {
                continue;
            }
            Color.printYellowInline("Enter password: ");
            String password = scanner.nextLine();
            if (!chekFild.chekPassword(password)) {
                continue;
            }
            Customer customer = new Customer(firstname, lastname, email, phonenumber, password);
            customers.add(customer);
            Color.printGreen("Registration was successful.");
            break;
        }
    }

    public void sellerRegister(Scanner scanner, ChekFild chekFild) {
        while (true) {
            Color.printWhiteBold("- - - - Seller Register - - - -");
            if (runBack(scanner) == 1) {
                break;
            }
            Color.printYellowInline("Enter firstname: ");
            String firstname = scanner.nextLine();
            Color.printYellowInline("Enter lastname: ");
            String lastname = scanner.nextLine();
            Color.printYellowInline("Enter Store Title: ");
            String storeTitle = scanner.nextLine();
            if (!chekFild.chekStoreTitle(storeTitle)) {
                continue;
            }
            Color.printYellowInline("Enter Code Mely: ");
            String codeMely = scanner.nextLine();
            if (!chekFild.chekCodeMely(codeMely)) {
                continue;
            }
            Color.printYellowInline("Enter phone number: ");
            String phonenumber = scanner.nextLine();
            if (!chekFild.chekPhonenumber(phonenumber)) {
                continue;
            }
            Color.printYellowInline("Enter password: ");
            String password = scanner.nextLine();
            if (!chekFild.chekPassword(password)) {
                continue;
            }
            Color.printYellowInline("Enter Province Of Sale: ");
            String provinceOfSale = scanner.nextLine();
            Seller seller = new Seller(firstname, lastname, storeTitle, codeMely, phonenumber, password,
                    provinceOfSale);
            seller.setAgencyCode(chekFild.generateAgencyCode());
            seller.setReasonForRejection("Your authentication has not been confirmed yet.");
            Supporter.getSellersVerification().add(seller);
            verification.add(seller);
            Color.printGreen("Seller registration completed. Waiting for verification.");
            break;
        }
    }

    private void sellerLogin(Scanner scanner) {
        while (true) {
            Color.printWhiteBold("- - - - Seller Login - - - -");
            if (runBack(scanner) == 1) {
                break;
            }
            Color.printYellowInline("Enter Agency code: ");
            String agencyCode = scanner.nextLine();
            Color.printYellowInline("Enter password: ");
            String password = scanner.nextLine();
            boolean conti = false;
            for (Seller seller : verification) {
                if (seller.getAgencyCode().equals(agencyCode)) {
                    if (seller.getPassword().equals(password)) {
                        Color.printYellow(seller.getReasonForRejection());
                        conti = true;
                        break;
                    }
                }
            }
            if (conti) {
                continue;
            }
            sellerLogin2(agencyCode, password, scanner);
        }
    }

    public void sellerLogin2(String agencyCode, String password, Scanner scanner) {
        boolean code = false, pas = false;
        for (Seller seller : sellers) {
            if (seller.getAgencyCode().equals(agencyCode)) {
                code = true;
                if (seller.getPassword().equals(password)) {
                    pas = true;
                    Color.printGreen("Successful login.");
                    SellerHelper sellerHelper = new SellerHelper(seller);
                    sellerHelper.menu(scanner);
                }
            }
        }
        if (!code) {
            Color.printRed("No user found with this Agency code.");
            return;
        }
        if (!pas) {
            Color.printRed("No user found with this Password.");
            return;
        }
    }

    public int runBack(Scanner scanner) {
        while (true) {
            Color.printWhiteBold("\nOptions:");
            Color.printCyan("1) Back");
            Color.printCyan("2) Exit");
            Color.printGreen("3) Continue");
            Color.printYellowInline("Please enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    return 1;
                case "2":
                    ExitVendilo.exit(scanner);
                case "3":
                    return 3;
                default:
                    Color.printRed("The selected option is invalid.");
                    break;
            }
        }
    }
}