package ir.ac.kntu;

public class Color {
    public static final String RESET = "\u001B[0m";

    // Regular Colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Background
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    // Bold
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";

    public static void printBlue(String message) {
        System.out.println(BLUE + message + RESET);
    }

    public static void printGreen(String message) {
        System.out.println(GREEN + message + RESET);
    }

    public static void printYellow(String message) {
        System.out.println(YELLOW + message + RESET);
    }

    public static void printRed(String message) {
        System.out.println(RED + message + RESET);
    }

    public static void printCyanBold(String message) {
        System.out.println(CYAN + BOLD + message + RESET);
    }

    public static void printWhiteBold(String message) {
        System.out.println(WHITE + BOLD + message + RESET);
    }

    public static void printRedBgWhite(String message) {
        System.out.println(RED + BG_WHITE + message + RESET);
    }

    // public static void main(String[] args) {
    //     printGreen("Sajad");
    // }
}
