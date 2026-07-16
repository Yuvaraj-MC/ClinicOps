import java.util.Scanner;

public class ScannerHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public static int readInt(String prompt) {
        System.out.print(prompt);
        return readInt();
    }

    public static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    // NEW: Generic method - works for ANY enum type (Shift, Specialization, etc.)
    public static <T extends Enum<T>> T readEnumChoice(String title, T[] values) {
        while (true) {
            System.out.println("\n" + title);
            for (int i = 0; i < values.length; i++) {
                System.out.println((i + 1) + ". " + values[i]);
            }
            int choice = readInt("Enter your choice: ");

            if (choice >= 1 && choice <= values.length) {
                return values[choice - 1];
            }
            System.out.println("Invalid choice. Please select between 1 and " + values.length + ".");
        }
    }
}