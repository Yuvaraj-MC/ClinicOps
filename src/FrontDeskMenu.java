public class FrontDeskMenu {

    private static final int PATIENT_REGISTRATION = 1;
    private static final int BOOK_APPOINTMENT = 2;
    private static final int LOGOUT = 3;

    public static void show() {
        boolean logout = false;

        while (!logout) {
            System.out.println("\n===== FRONT DESK MENU =====");
            System.out.println("1. Patient Registration");
            System.out.println("2. Book Appointment");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");

            int choice = ScannerHelper.readInt();

            switch (choice) {
                case PATIENT_REGISTRATION:
                    System.out.println(">> Patient Registration - logic coming in a future use case.");
                    break;
                case BOOK_APPOINTMENT:
                    System.out.println(">> Book Appointment - logic coming in a future use case.");
                    break;
                case LOGOUT:
                    System.out.println("Logging out from Front Desk. Returning to main menu...");
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2 or 3.");
            }
        }
    }
}