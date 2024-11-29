import java.io.*;
import java.util.*;

public class ExpenseTracker {
    private static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Calculate Total Expenses");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> addExpense(scanner);
                case 2 -> viewExpenses();
                case 3 -> calculateTotalExpenses();
                case 4 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addExpense(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            System.out.print("Enter expense description: ");
            String description = scanner.nextLine();
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            writer.write(description + "," + amount + "," + date);
            writer.newLine();
            System.out.println("Expense added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void viewExpenses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- All Expenses ---");
            System.out.printf("%-20s %-10s %-10s%n", "Description", "Amount", "Date");
            System.out.println("--------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.printf("%-20s %-10s %-10s%n", parts[0], parts[1], parts[2]);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void calculateTotalExpenses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            double total = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                total += Double.parseDouble(parts[1]);
            }
            System.out.printf("Total Expenses: %.2f%n", total);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}
