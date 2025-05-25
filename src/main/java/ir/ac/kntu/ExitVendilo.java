package ir.ac.kntu;

import java.util.Scanner;

public class ExitVendilo {

    public static void exit(Scanner scanner) {
        scanner.close();
        Color.printBlue("Exiting the application...");
        Color.printCyanBold("We look forward to seeing you again.");
        System.exit(0);
    }
}
