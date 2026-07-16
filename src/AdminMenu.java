public class AdminMenu {

    private static final int DOCTOR_ENTRY = 1;
    private static final int BULK_ENTRY = 2;
    private static final int VIEW_AUDIT = 3;
    private static final int LOGOUT = 4;

    public static void show() {
        boolean logout = false;

        while (!logout) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Doctor's Data Entry");
            System.out.println("2. Bulk Data Entry (from .csv file)");
            System.out.println("3. View Audit Logs");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            int choice = ScannerHelper.readInt();

            switch (choice) {
                case DOCTOR_ENTRY:
                    System.out.println(">> Doctor Data Entry - logic coming in a future use case.");
                    break;
                case BULK_ENTRY:
                    System.out.println(">> Bulk Data Entry - logic coming in a future use case.");
                    break;
                case VIEW_AUDIT:
                    System.out.println(">> View Audit Logs - logic coming in a future use case.");
                    break;
                case LOGOUT:
                    System.out.println("Logging out from Admin. Returning to main menu...");
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3 or 4.");
            }
        }
    }
}