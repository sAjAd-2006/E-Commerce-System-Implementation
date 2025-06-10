package ir.ac.kntu;

import java.util.Scanner;

public class CustomerSerchFilterHelper {
    public static String searchForProductType(Scanner scanner) {
        System.out.println("\nProduct Type:\n1)Book\n2)Digital Goods\n3)ALL(Default)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                return "Book";
            case "2":
                System.out.println("\nDigital Goods Type:\n1)Mobile\n2)Laptop\n3)ALL Digital Goods(Default)");
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        return "Mobile";
                    case "2":
                        return "Laptop";
                    default:
                        return "Digital Goods";
                }
            default:
                return "ALL";
        }
    }

    public static int priceRange(String val, Scanner scanner, int min) {
        while (true) {
            System.out.print(val + ": ");
            String range = scanner.nextLine();
            if ("".equals(range)) {
                if ("max".equalsIgnoreCase(val)) {
                    return Integer.MAX_VALUE;
                } else {
                    if ("min".equalsIgnoreCase(val)) {
                        return 0;
                    }
                }
            } else {
                if (isInteger(range)) {
                    if (Integer.parseInt(range) < min) {
                        if ("max".equalsIgnoreCase(val)) {
                            System.out.println("The maximum must not be less than the " + min);
                        } else {
                            System.out.println("The miximum must not be less than the 0.");
                        }
                        continue;
                    }
                    return Integer.parseInt(range);
                } else {
                    System.out.println("invild input. enter a number");
                }
            }
        }
    }

    public static double rateRange(String val, Scanner scanner, double min) {
        while (true) {
            System.out.print(val + ": ");
            String range = scanner.nextLine();
            if ("".equals(range)) {
                if ("maxRate".equalsIgnoreCase(val)) {
                    return 5.0;
                } else {
                    if ("minRate".equalsIgnoreCase(val)) {
                        return 1.0;
                    }
                }
            } else {
                if (isDouble(range)) {
                    if (Double.parseDouble(range) < min) {
                        if ("maxRate".equalsIgnoreCase(val)) {
                            System.out.println("The maximum must not be less than the " + min);
                        } else {
                            System.out.println("The miximum must not be less than the 1.0");
                        }
                        continue;
                    }
                    if (Double.parseDouble(range) > 5.0) {
                        System.out.println("The extremum cannot be greater than 5.0");
                        continue;
                    }
                    return Double.parseDouble(range);
                } else {
                    System.out.println("invild input. enter a number");
                }
            }
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
