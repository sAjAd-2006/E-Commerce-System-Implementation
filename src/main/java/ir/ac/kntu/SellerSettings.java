package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellerSettings {
    private List<Customer> customers;
    private List<Seller> sellers;
    private List<Seller> verification;

    public SellerSettings(List<Customer> customers, List<Seller> sellers, List<Seller> verification) {
        this.customers = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.verification = new ArrayList<>();
        this.customers = customers;
        this.sellers = sellers;
        this.verification = verification;
    }

    public void settings(Seller user, Scanner scanner) {
        settingHelp(user, scanner);
        settingHelp3(user, scanner);
        System.out.print("Change password? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            while (true) {
                System.out.print("Enter new password: ");
                String password = scanner.nextLine();
                if (("".equals(password))) {
                    break;
                }
                if (!chekPassword(password)) {
                    continue;
                } else {
                    user.setPassword(scanner.nextLine());
                    break;
                }
            }
        }
        user.setReasonForRejection("Your authentication has not been confirmed yet.");
        System.out.println("\nUpdated Info:\n" + user + user.chap());
    }

    public void settingHelp3(Seller user, Scanner scanner) {
        System.out.print("Change phone number? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            while (true) {
                System.out.print("Enter new phone number: ");
                String phonenumber = scanner.nextLine();
                if (("".equals(phonenumber))) {
                    break;
                }
                if (!chekPhonenumber(phonenumber)) {
                    continue;
                } else {
                    user.setPhonenumber(phonenumber);
                    break;
                }
            }
        }
    }

    public void settingHelp(Seller user, Scanner scanner) {
        System.out.print("Change first name? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            System.out.print("Enter new first name: ");
            user.setFirstname(scanner.nextLine());
        }
        System.out.print("Change last name? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            System.out.print("Enter new last name: ");
            user.setLastname(scanner.nextLine());
        }
        settingHelp2(user, scanner);
        System.out.print("Change province Of Sale? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            System.out.print("Enter new province Of Sale: ");
            user.setProvinceOfSale(scanner.nextLine());
        }
    }

    public void settingHelp2(Seller user, Scanner scanner) {
        System.out.print("Change Store Title? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            while (true) {
                System.out.print("Enter new Store Title: ");
                String storeTitle = scanner.nextLine();
                if (("".equals(storeTitle))) {
                    break;
                }
                if (!chekStoreTitle(storeTitle)) {
                    continue;
                } else {
                    user.setStoreTitle(storeTitle);
                    break;
                }
            }
        }
    }

    public boolean chekPhonenumber(String phonenumber) {
        String regex = "^(\\+98|0)?9\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phonenumber);
        if (!matcher.find()) {
            Color.printRed("The phone number format is incorrect.\nTry again.");
            return false;
        } else {
            for (Customer customer : customers) {
                if (customer.getPhonenumber().equals(phonenumber)) {
                    Color.printRed("The phone number is duplicate.\nTry again.");
                    return false;
                }
            }
            for (Seller seller : sellers) {
                if (seller.getPhonenumber().equals(phonenumber)) {
                    Color.printRed("The phone number is duplicate.\nTry again.");
                    return false;
                }
            }
            for (Seller seller : verification) {
                if (seller.getPhonenumber().equals(phonenumber)) {
                    Color.printRed("The phone number is duplicate.\nTry again.");
                    return false;
                }
            }
            return true;
        }
    }

    public boolean chekStoreTitle(String storeTitle) {
        for (Seller seller : sellers) {
            if (seller.getStoreTitle().equals(storeTitle)) {
                Color.printRed("The Store Title is duplicate.\nTry again.");
                return false;
            }
        }
        for (Seller seller : verification) {
            if (seller.getStoreTitle().equals(storeTitle)) {
                Color.printRed("The Store Title is duplicate.\nTry again.");
                return false;
            }
        }
        return true;
    }

    public boolean chekPassword(String password) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            Color.printRed("The password format is incorrect.\nTry again.");
            return false;
        } else {
            return true;
        }
    }
}
