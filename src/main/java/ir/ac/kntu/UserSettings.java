package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSettings {
    private List<Customer> customers;
    private List<Seller> sellers;
    private List<Seller> verification;

    public UserSettings(List<Customer> customers, List<Seller> sellers, List<Seller> verification) {
        this.customers = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.verification = new ArrayList<>();
        this.customers = customers;
        this.sellers = sellers;
        this.verification = verification;
    }

    public void settings(Customer user, Scanner scanner) {
        settingHelp(user, scanner);
        settingHelp2(user, scanner);
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
        System.out.println("\nUpdated Info:\n" + user + " phone number: " + user.getPhonenumber()
                + " password: " + user.getPassword());
    }

    public void settingHelp(Person user, Scanner scanner) {
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
        System.out.print("Change email? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            while (true) {
                System.out.print("Enter new email: ");
                String email = scanner.nextLine();
                if (("".equals(email))) {
                    break;
                }
                if (!chekEmail(email)) {
                    continue;
                } else {
                    user.setEmail(email);
                    break;
                }
            }
        }
        // System.out.print("Change email? (y/n): ");
    }

    public void settingHelp2(Person user, Scanner scanner) {
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

    public boolean chekEmail(String email) {
        String regex = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            Color.printRed("The email format is incorrect.\nTry again.");
            return false;
        } else {
            for (Customer customer : customers) {
                if (customer.getEmail().equals(email)) {
                    Color.printRed("The email is duplicate.\nTry again.");
                    return false;
                }
            }
            return true;
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
