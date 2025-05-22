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
        int currentPage = page;
        int totalPages = (int) Math.ceil(list.size() / (double) pageSize);

        while (true) {
            showPage(currentPage);
            String command = getValidCommand();

            switch (command) {
                case "next" -> {
                    if (currentPage < totalPages - 1)
                        currentPage++;
                    else
                        System.out.println("You are already on the last page.");
                }
                case "prev" -> {
                    if (currentPage > 0)
                        currentPage--;
                    else
                        System.out.println("You are already on the first page.");
                }
                case "select" -> {
                    int start = currentPage * pageSize;
                    int end = Math.min(start + pageSize, list.size());
                    System.out.print("Enter item number between " + (start + 1) + " and " + end + ": ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        if (index >= start && index < end)
                            return index;//list.get(index);
                        else
                            System.out.println("Selection out of range.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format.");
                    }
                }
                case "back" -> {
                    System.out.println("Back without selection.");
                    return -1;
                }
                case "exit" -> System.exit(0);
            }
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
            if (cmd.matches("next|prev|select|back|exit"))
                return cmd;
            System.out.println("Invalid command.");
        }
    }
}
