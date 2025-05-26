package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSettings {
    private List<Customer> customers;

    public UserSettings() {
        this.customers = new ArrayList<>();
        this.customers = Vendilo.getCustomers();
    }

    public void settings(Customer user, Scanner scanner) {
        settingHelp(user, scanner);
        System.out.print("Change phone number? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            while (true) {
                System.out.print("Enter new phone number: ");
                String phonenumber = scanner.nextLine();
                if (!chekPhonenumber(phonenumber)) {
                    continue;
                } else {
                    user.setPhonenumber(phonenumber);
                    break;
                }
            }
        }
        System.out.print("Change password? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            while (true) {
                System.out.print("Enter new password: ");
                String password = scanner.nextLine();
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
                if (!chekEmail(email)) {
                    continue;
                } else {
                    user.setEmail(email);
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

    public boolean chekPhonenumber(String phonenumber) {
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

    public boolean chekPassword(String password) {
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
}
