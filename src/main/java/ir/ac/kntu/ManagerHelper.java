package ir.ac.kntu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerHelper {
    private Manager manager;
    private Vendilo vendilo;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ManagerHelper(Manager manager, Vendilo vendilo) {
        this.manager = manager;
        this.vendilo = vendilo;
    }

    public void menu(Scanner scanner) {
        boolean runMenu = true;
        while (runMenu) {
            System.out.println("- - - - Manager Menu - - - -\n1-Add administrator\n2-Add supporter");
            System.out.println("3-User management\n4-Reviewing sales performance\n5-User performance review");
            System.out.println("6-Create a universal discount code\n7-Sending a public message\n => ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addAdministrator(scanner);
                case "2" -> addSupporter(scanner);
                case "3" -> userManagement(scanner);
                case "4" -> salesPerformance(scanner);
                case "5" -> userPerformance(scanner);
                case "6" -> crDiscountCode(null, scanner, true);
                case "7" -> sendingAPublicMessage(scanner);
                default -> System.out.println("The selected option is invalid.");
            }
        }
    }

    private void sendingAPublicMessage(Scanner scanner) {
        System.out.println("Enter your message: ");
        UniversalNotification universNotif = new UniversalNotification(scanner.nextLine());
        for (Customer customer : vendilo.getCustomers()) {
            customer.getNotifications().add(universNotif);
        }
    }

    private void userPerformance(Scanner scanner) {
        Color.printYellow("Do you have a specific user in mind? y/n(default)");
        String choice = scanner.nextLine();
        if ("y".equalsIgnoreCase(choice)) {
            System.out.print("Enter the desired user email: ");
            choice = scanner.nextLine();
            for (Customer customer : vendilo.getCustomers()) {
                if (customer.getEmail().equals(choice)) {
                    System.out.println(customer + " Last month's sales: " + customer.shoppingMuch());
                    customerRiward(customer, scanner);
                }
            }
        } else {
            Paginator<Customer> paginator = new Paginator<>(vendilo.getCustomers(), 10);
            int select = paginator.paginate(0);
            if (select == -1) {
                return;
            }
            Customer customer = vendilo.getCustomers().get(select);
            System.out.println(customer + " Last month's sales: " + customer.shoppingMuch());
            customerRiward(customer, scanner);
        }
    }

    private void customerRiward(Customer customer, Scanner scanner) {
        Color.printYellow("Would you like to give the user a discount code? y/n(default)");
        String choice = scanner.nextLine();
        if ("y".equalsIgnoreCase(choice)) {
            crDiscountCode(customer, scanner, false);
        }
        Color.printYellow("Would you like to make the user a member of Vandilo Plus? y/n(default)");
        choice = scanner.nextLine();
        if ("y".equalsIgnoreCase(choice)) {
            while (true) {
                System.out.println("Enter the number of months of membership (0 < x): ");
                choice = scanner.nextLine();
                if (!isInteger(choice) || Integer.parseInt(choice) <= 0) {
                    Color.printRed("format error");
                    continue;
                }
                customer.setVendiloPlus(true);
                if (vendilo.getVendiloPlus().containsKey(customer)) {
                    LocalDate localDate = vendilo.getVendiloPlus().get(customer);
                    vendilo.getVendiloPlus().put(customer, localDate.plusMonths(Integer.parseInt(choice)));
                } else {
                    vendilo.getVendiloPlus().put(customer, LocalDate.now().plusMonths(Integer.parseInt(choice)));
                }
                break;
            }
        }
    }

    private void crDiscountCode(Customer customer, Scanner scanner, Boolean all) {
        DiscountCode discountCode = new DiscountCode("1", Kind.Numeric, 10, 1);
        System.out.print("Enter discount code: ");
        discountCode.setCode(scanner.nextLine());
        crDiscountCodeSwich(discountCode, scanner);
        if (!all) {
            while (true) {
                System.out.print("Enter number of times it can be used (0 < x): ");
                String choice = scanner.nextLine();
                if (!isInteger(choice) || Integer.parseInt(choice) <= 0) {
                    Color.printRed("format error");
                    continue;
                }
                discountCode.setNumbCanBeUsed(Integer.parseInt(choice));
                break;
            }
            crNotificationAbutDiscountCode(customer, discountCode);
        } else {
            crNotificationAbutDiscountCodeForAll(discountCode);
        }
    }

    private void crNotificationAbutDiscountCodeForAll(DiscountCode discountCode) {
        DiscountCodeNotification dCNotif = new DiscountCodeNotification(discountCode);
        dCNotif.setCanSeeOrNot(true);
        for (Customer customer : vendilo.getCustomers()) {
            customer.getNotifications().add(dCNotif);
        }
    }

    private void crNotificationAbutDiscountCode(Customer customer, DiscountCode discountCode) {
        DiscountCodeNotification dCNotif = new DiscountCodeNotification(discountCode);
        dCNotif.setCanSeeOrNot(true);
        customer.getNotifications().add(dCNotif);
    }

    private void crDiscountCodeSwich(DiscountCode discountCode, Scanner scanner) {
        System.out.print("Enter kind: 1.Percentage 2.Numeric(default)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> crDiscountCodeSwichCase1(discountCode, scanner);
            default -> {
                discountCode.setKind(Kind.Numeric);
                while (true) {
                    System.out.print("Enter Percentage (0 < x): ");
                    choice = scanner.nextLine();
                    if (!isInteger(choice) || Integer.parseInt(choice) <= 0) {
                        Color.printRed("format error");
                        continue;
                    }
                    discountCode.setDiscountAmount(Integer.parseInt(choice));
                    break;
                }
            }
        }
    }

    private void crDiscountCodeSwichCase1(DiscountCode discountCode, Scanner scanner) {
        discountCode.setVahed("%");
        discountCode.setKind(Kind.Percentage);
        while (true) {
            System.out.print("Enter Percentage (0 < x <= 100): ");
            String choice = scanner.nextLine();
            if (!isInteger(choice) || Integer.parseInt(choice) <= 0 || Integer.parseInt(choice) > 100) {
                Color.printRed("format error");
                continue;
            }
            discountCode.setDiscountAmount(Integer.parseInt(choice));
            break;
        }
    }

    private void salesPerformance(Scanner scanner) {
        Color.printYellow("Do you have a specific seller in mind? y/n(default)");
        String choice = scanner.nextLine();
        if ("y".equalsIgnoreCase(choice)) {
            System.out.print("Enter the desired vendor code: ");
            choice = scanner.nextLine();
            for (Seller seller : vendilo.getSellers()) {
                if (seller.getAgencyCode().equals(choice)) {
                    System.out.println(seller + " Last month's sales: " + seller.sellPerformance());
                    sellerRiward(seller, scanner);
                }
            }
        } else {
            Paginator<Seller> paginator = new Paginator<>(vendilo.getSellers(), 10);
            int select = paginator.paginate(0);
            if (select == -1) {
                return;
            }
            Seller seller = vendilo.getSellers().get(select);
            System.out.println(seller + " Last month's sales: " + seller.sellPerformance());
            sellerRiward(seller, scanner);
        }
    }

    private void sellerRiward(Seller seller, Scanner scanner) {
        System.out.println("Would you like to reward the seller? y/n(default)");
        String choice = scanner.nextLine();
        if ("y".equalsIgnoreCase(choice)) {
            while (true) {
                System.out.print("Enter the bonus amount: ");
                choice = scanner.nextLine();
                if (isInteger(choice) && Integer.parseInt(choice) > 0) {
                    seller.getWallet().addToWallet(Integer.parseInt(choice));
                    return;
                } else {
                    System.out.println("Incorrect input. Enter a number.\n");
                }
            }
        }
    }

    private void userManagement(Scanner scanner) {
        List<Person> allPersons = new ArrayList<>();
        allPersons = filterByClass(scanner);
        Paginator<Person> paginator = new Paginator<>(allPersons, 10);
        while (true) {
            int select = paginator.paginate(0);
            if (select == -1) {
                return;
            }
            Color.printWhiteBold(allPersons.get(select).toString());
            System.out.println("1>Edit 2>Blocking 3>Back(default)\n=> ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> editUser(allPersons.get(select), scanner);
                case "2" -> {
                    Person person = allPersons.get(select);
                    if ((person instanceof Manager)
                            && ((Manager) person).getAccessLevel() >= this.manager.getAccessLevel()) {
                        Color.printRed("You do not have sufficient access to ban.");
                    } else {
                        person.setBan(true);
                    }
                }
                default -> System.out.println();
            }
        }
    }

    private void editUser(Person person, Scanner scanner) {
        if ((person instanceof Manager) && ((Manager) person).getAccessLevel() >= this.manager.getAccessLevel()) {
            Color.printRed("You do not have sufficient access to edit the information.");
            return;
        }
        System.out.println("- - - - - edit info - - - - -");
        System.out.print("Change first name? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            System.out.print("Enter new first name: ");
            person.setFirstname(scanner.nextLine());
        }
        System.out.print("Change last name? (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            System.out.print("Enter new last name: ");
            person.setLastname(scanner.nextLine());
        }
        if ((person instanceof Supporter) || (person instanceof Manager)) {
            System.out.print("Change agency name? (y/n): ");
            if ("y".equalsIgnoreCase(scanner.nextLine())) {
                System.out.print("Enter new agency name: ");
                String agencyName = scanner.nextLine();
                if ((person instanceof Supporter) && chekAgencyNameSuporter(agencyName)) {
                    ((Supporter) person).setAgencyName(agencyName);
                }
                if ((person instanceof Manager) && chekAgencyNameManager(agencyName)) {
                    ((Manager) person).setAgencyName(agencyName);
                }
            }
        }
        editUser2(person, scanner);
    }

    private void editUser2(Person person, Scanner scanner) {
        if (person instanceof Supporter) {
            System.out.print("Change supporter activity sections? (y/n): ");
            if ("y".equalsIgnoreCase(scanner.nextLine())) {
                Supporter supporter = (Supporter) person;
                System.out.print("Enter new supporter activity sections:\n1."
                        + Report.Discrepancy_between_order_and_delivered_product + "\n2." + Report.Not_receiving_order
                        + "\n3." + Report.Product_qualit + "\n4." + Report.Settings + "\n5.continue");
                boolean run = true;
                while (run) {
                    String choice = scanner.nextLine();
                    if (!"5".equals(choice)) {
                        supporter.getActivityMap().remove(Report.ALL, 1);
                    }
                    switch (choice) {
                        case "1" ->
                            supporter.getActivityMap().put(Report.Discrepancy_between_order_and_delivered_product, 1);
                        case "2" -> supporter.getActivityMap().put(Report.Not_receiving_order, 1);
                        case "3" -> supporter.getActivityMap().put(Report.Product_qualit, 1);
                        case "4" -> supporter.getActivityMap().put(Report.Settings, 1);
                        default -> {
                            System.out.println(supporter.getActivityMap());
                            run = false;
                        }
                    }
                }
            }
        }
        Color.printWhiteBold(person.toString());
    }

    private List<Person> filterByName(List<Person> allPersons, Scanner scanner) {
        List<Person> allPersons2 = new ArrayList<>();
        Color.printYellow("Do you have a specific user in mind? y/n(default)");
        String choice = scanner.nextLine();
        if ("y".equalsIgnoreCase(choice)) {
            Color.printYellowInline("Enter firstname: ");
            String firstname = scanner.nextLine();
            Color.printYellowInline("Enter lastname: ");
            String lastname = scanner.nextLine();
            for (Person person : allPersons) {
                if (person.getFirstname().equalsIgnoreCase(firstname)
                        && person.getLastname().equalsIgnoreCase(lastname)) {
                    allPersons2.add(person);
                }
            }
            return allPersons2;
        } else {
            return allPersons;
        }
    }

    private List<Person> filterByClass(Scanner scanner) {
        List<Person> allPersons = new ArrayList<>();
        System.out.println(
                "What roles are you considering?\n1.customer\n2.seller\n3.supporter\n4.manager\n5.All(default)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                for (Customer customer : vendilo.getCustomers()) {
                    allPersons.add(customer);
                }
            }
            case "2" -> {
                for (Seller seller : vendilo.getSellers()) {
                    allPersons.add(seller);
                }
            }
            case "3" -> {
                for (Supporter supporter : vendilo.getSupporters()) {
                    allPersons.add(supporter);
                }
            }
            case "4" -> {
                for (Manager manager : vendilo.getManagers()) {
                    allPersons.add(manager);
                }
            }
            default -> allPersons = filterByClassSwiceDefault(allPersons);
        }
        return filterByName(allPersons, scanner);
    }

    private List<Person> filterByClassSwiceDefault(List<Person> allPersons) {
        for (Customer customer : vendilo.getCustomers()) {
            allPersons.add(customer);
        }
        for (Seller seller : vendilo.getSellers()) {
            allPersons.add(seller);
        }
        for (Supporter supporter : vendilo.getSupporters()) {
            allPersons.add(supporter);
        }
        for (Manager manager : vendilo.getManagers()) {
            allPersons.add(manager);
        }
        return allPersons;
    }

    private void addAdministrator(Scanner scanner) {
        if (this.manager.getAccessLevel() <= 2) {
            Color.printRed("You do not have sufficient permissions to create a new administrator.");
            return;
        }
        while (true) {
            Color.printWhiteBold("- - - - Add Administrator - - - -");
            if (runBack(scanner) == 1) {
                break;
            }
            Color.printYellowInline("Enter firstname: ");
            String firstname = scanner.nextLine();
            Color.printYellowInline("Enter lastname: ");
            String lastname = scanner.nextLine();
            Color.printYellowInline("Enter agencyName: ");
            String agencyName = scanner.nextLine();
            if (!chekAgencyNameManager(agencyName)) {
                continue;
            }
            Color.printYellowInline("Enter password: ");
            String password = scanner.nextLine();
            if (!chekPassword(password)) {
                Color.printRed("password format error");
                continue;
            }
            Manager newManager = new Manager(firstname, lastname, agencyName, password,
                    this.manager.getAccessLevel() - 1);
            vendilo.getManagers().add(newManager);
        }
    }

    private void addSupporter(Scanner scanner) {
        while (true) {
            Color.printWhiteBold("- - - - Add Supporter - - - -");
            if (runBack(scanner) == 1) {
                break;
            }
            Color.printYellowInline("Enter firstname: ");
            String firstname = scanner.nextLine();
            Color.printYellowInline("Enter lastname: ");
            String lastname = scanner.nextLine();
            Color.printYellowInline("Enter agencyName: ");
            String agencyName = scanner.nextLine();
            if (!chekAgencyNameSuporter(agencyName)) {
                continue;
            }
            Color.printYellowInline("Enter password: ");
            String password = scanner.nextLine();
            if (!chekPassword(password)) {
                Color.printRed("password format error");
                continue;
            }
            Supporter newSupporter = new Supporter(firstname, lastname, agencyName, password);
            vendilo.getSupporters().add(newSupporter);
        }
    }

    private boolean chekAgencyNameManager(String agencyName) {
        for (Manager manager : vendilo.getManagers()) {
            if (manager.getAgencyName().equals(agencyName)) {
                Color.printRed("The agency name is duplicate.\nTry again.");
                return false;
            }
        }
        return true;
    }

    private boolean chekAgencyNameSuporter(String agencyName) {
        for (Supporter supporter : vendilo.getSupporters()) {
            if (supporter.getAgencyName().equals(agencyName)) {
                Color.printRed("The agency name is duplicate.\nTry again.");
                return false;
            }
        }
        return true;
    }

    private int runBack(Scanner scanner) {
        while (true) {
            System.out.println("Please enter the desired option : 1)Back 2)Exit 3)Continue");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    return 1;
                case "2":
                    System.exit(0);
                case "3":
                    return 3;
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    private boolean chekPassword(String password) {
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

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
