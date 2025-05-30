package ir.ac.kntu;

import java.util.*;

public class Paginator<T> {
    private final List<T> list;
    private final int pageSize;
    private final Scanner scanner;

    public Paginator(List<T> list, int pageSize) {
        this.list = list;
        this.pageSize = pageSize;
        this.scanner = new Scanner(System.in);
    }

    public int paginate(int page) {
        int currentPage = page, totalPages = (int) Math.ceil(list.size() / (double) pageSize);
        while (true) {
            showPage(currentPage);
            String command = getValidCommand();
            switch (command) {
                case "next" -> {
                    if (currentPage < totalPages - 1) {
                        currentPage++;
                    } else {
                        System.out.println("You are already on the last page.");
                    }
                }
                case "prev" -> {
                    currentPage = casePrev(currentPage);
                }
                case "select" -> {
                    int select = caseySelect(currentPage, scanner);
                    if (select != -1) {
                        return select;
                    }
                }
                case "back" -> {
                    System.out.println("Back without selection.\n");
                    return -1;
                }
                case "exit" -> ExitVendilo.exit(scanner);
                default -> System.out.println("errrrror");
            }
        }
    }

    public int casePrev(int currentPage) {
        if (currentPage > 0) {
            currentPage--;
        } else {
            System.out.println("You are already on the first page.");
        }
        return currentPage;
    }

    public int caseySelect(int currentPage, Scanner scanner) {
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, list.size());
        System.out.print("Enter item number between " + (start + 1) + " and " + end + ": ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index >= start && index < end) {
                return index;// list.get(index);
            } else {
                System.out.println("Selection out of range.");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
            return -1;
        }
    }

    public void showPage(int page) {
        int start = page * pageSize;
        int end = Math.min(start + pageSize, list.size());
        System.out.println("\nShowing items " + (start + 1) + " to " + end + ":");
        for (int i = start; i < end; i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    public String getValidCommand() {
        while (true) {
            System.out.print("\nEnter command (next / prev / select / back / exit): ");
            String cmd = scanner.nextLine().trim().toLowerCase();
            if (cmd.matches("next|prev|select|back|exit")) {
                return cmd;
            }
            System.out.println("Invalid command.");
        }
    }
}
