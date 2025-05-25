package ir.ac.kntu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TimeFilterHelper<T extends Timeable> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public List<T> filterTimesByUserInput(List<T> transactions, Scanner scanner) {
        LocalDateTime start, end;
        // Scanner scanner = new Scanner(System.in);

        while (true) {
            start = readDateTime(scanner, "Enter start time (yyyy-MM-dd HH:mm): ");
            end = readDateTime(scanner, "Enter end time (yyyy-MM-dd HH:mm): ");

            if (end.isBefore(start)) {
                System.out.println("Error: End time must be after start time. Please try again.\n");
            } else {
                break;
            }
        }

        LocalDateTime finalStart = start;
        LocalDateTime finalEnd = end;

        return transactions.stream()
                .filter(e -> !e.getLocalDateTime().isBefore(finalStart) && !e.getLocalDateTime().isAfter(finalEnd))
                .collect(Collectors.toList());
    }

    public LocalDateTime readDateTime(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter the date and time as yyyy-MM-dd HH:mm.\n");
            }
        }
    }
}