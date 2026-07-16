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

    // Reads and validates an Indian mobile number (10 digits, starts with 6-9)
    public static String readMobileNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Indian mobile: starts with 6,7,8 or 9 and has exactly 10 digits
            if (input.matches("[6-9][0-9]{9}")) {
                return input;
            }
            System.out.println("Invalid mobile number. Enter a 10-digit number starting with 6-9.");
        }
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