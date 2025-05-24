package ir.ac.kntu;

import java.util.Scanner;

public final class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String nextLine() {
        return scanner.nextLine();
    }

    public static int nextInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Enter a number.");
            return nextInt();
        }
    }

    public static void closeScanner() {
        scanner.close();
    }
}