package ir.ac.kntu;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChekFild {
    private List<Seller> sellers;
    private List<Customer> customers;

    public ChekFild(List<Seller> sellers, List<Customer> customers) {
        this.sellers = sellers;
        this.customers = customers;
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

    public boolean chekStoreTitle(String storeTitle) {
        for (Seller seller : sellers) {
            if (seller.getStoreTitle().equals(storeTitle)) {
                Color.printRed("The Store Title is duplicate.\nTry again.");
                return false;
            }
        }
        return true;
    }

    public boolean chekCodeMely(String codeMely) {
        for (Seller seller : sellers) {
            if (seller.getCodeMely().equals(codeMely)) {
                Color.printRed("The Code Mely is duplicate.\nTry again.");
                return false;
            }
        }
        return true;
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
            return true;
        }
    }

    public String generateAgencyCode() {
        String alpha = "QWE0RT789YUIOPAS156DFGHJKLZXC234VBNM";
        Random random = new Random();
        String ramz = "";
        boolean duplicate = true;
        while (duplicate) {
            ramz = "";
            for (int i = 0; i < 6; i++) {
                int randInt = random.nextInt(36);
                ramz += alpha.substring(randInt, randInt + 1);
            }
            duplicate = false;
            for (Seller seller : sellers) {
                if (seller.getAgencyCode().equals(ramz)) {
                    duplicate = true;
                    break;
                }
            }
        }
        Color.printCyan("This is your Agency code: " + Color.CYAN_BOLD + ramz);
        return ramz;
    }
}
